package android.com.fellowchef.repository

import android.com.fellowchef.database.RecipeDAO
import android.com.fellowchef.database.model.RecipeCategory
import android.com.fellowchef.repository.models.*
import android.com.fellowchef.service.FellowChefRecipeService
import android.com.fellowchef.ui.recipe.Recipe
import android.com.fellowchef.util.safeExecute
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    private val apiService: FellowChefRecipeService,
    private val recipeDAO: RecipeDAO) {



    fun getRecipesFeed(viewModelScope: CoroutineScope): LiveData<Resource<List<Recipe>>> {
        //Return using object expression from abstract super class
        return object : NetworkResource<List<Recipe>>(viewModelScope) {
            override suspend fun loadFromDisk(): LiveData<List<Recipe>> {
                return MutableLiveData(recipeDAO.getRecipes())
            }

            override suspend fun shouldFetch(data: List<Recipe>?): Boolean {
                Log.i(TAG, "shouldFetch...${data.isNullOrEmpty()}")
                return true
            }

            override suspend fun fetchData(): Response<List<Recipe>> {

                val response = apiService.getRecipes().safeExecute()

                if (!response.isSuccessful || response.body().isNullOrEmpty()) {
                    return Failure(400, "Invalid Response")
                }
                return Success(response.body()!!)


            }

            override suspend fun saveToDisk(data: List<Recipe>): Boolean {
                Log.i(TAG, "saveToDisk...")

                val ids = recipeDAO.updateData(data)
                return ids.isNotEmpty()
            }

        }.asLiveData()
    }

    fun getRecipeFiltersFeed(viewModelScope: CoroutineScope) : LiveData<Resource<List<RecipeCategory>>> {
        return object: NetworkResource<List<RecipeCategory>>(viewModelScope){
            override suspend fun loadFromDisk(): LiveData<List<RecipeCategory>> {
                val filters = MutableLiveData(recipeDAO.getRecipeFilters())
                Log.i(TAG, "loadFromDisk..filters... ${filters.value}")
                return filters
            }

            override suspend fun shouldFetch(data: List<RecipeCategory>?): Boolean {
                return data.isNullOrEmpty()
            }

            override suspend fun fetchData(): Response<List<RecipeCategory>> {
                val response =apiService.getRecipeFilters().safeExecute()

                if (!response.isSuccessful || response.body().isNullOrEmpty()) {
                    return Failure(400, "Invalid Response")
                }
                return Success(response.body()!!)
            }

            override suspend fun saveToDisk(data: List<RecipeCategory>): Boolean {
                //Do nothing
                val ids = recipeDAO.updateFilterData(data)
                return ids.isNotEmpty()
            }

        }.asLiveData()

    }

    suspend fun addRecipeToLiked(recipeId: Int): Long {
        return recipeDAO.addToLikedRecipes(recipeId)
    }

    suspend fun removeRecipeFromLiked(recipeId: Int) {
        return recipeDAO.removeRecipeFromLiked(recipeId)
    }

    suspend fun getRecipeIdsLiked(): List<Int>{
        return recipeDAO.getRecipeIdsLiked()
    }

    companion object {
        const val TAG = "RecipeRepository"
    }
}