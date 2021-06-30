package android.com.fellowchef.repository

import android.com.fellowchef.database.RecipeDAO
import android.com.fellowchef.repository.models.*
import android.com.fellowchef.service.FellowChefRecipeApi
import android.com.fellowchef.service.FellowChefRecipeService
import android.com.fellowchef.ui.recipe.Recipe
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope

class RecipeRepository(
    private val recipeService: FellowChefRecipeService,
    private val recipeDAO: RecipeDAO,
    private val viewModelScope: CoroutineScope
) {

    fun getRecipesFeed(): LiveData<Resource<List<Recipe>>> {
        //Return using object expression from abstract super class
        return object : NetworkResource<List<Recipe>, String>(viewModelScope) {
            override suspend fun loadFromDisk(): LiveData<List<Recipe>> {
                val dataFromDisk = MutableLiveData(recipeDAO.getRecipes())
                return dataFromDisk
            }

            override suspend fun shouldFetch(data: List<Recipe>?): Boolean {
                Log.i(TAG, "shouldFetch...${data.isNullOrEmpty()}")
                return data.isNullOrEmpty()
            }

            override suspend fun fetchData(): Response<List<Recipe>> {

                val response = FellowChefRecipeApi.retrofitService.getRecipes().execute()

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

    companion object {
        const val TAG = "RecipeRepository"
    }
}