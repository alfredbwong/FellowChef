package android.com.fellowchef.ui.home

import android.com.fellowchef.database.RecipeDatabase
import android.com.fellowchef.di.HomeRecipeActivityScope
import android.com.fellowchef.repository.RecipeRepository
import android.com.fellowchef.repository.models.Resource
import android.com.fellowchef.service.BASE_URL
import android.com.fellowchef.service.FellowChefRecipeApi
import android.com.fellowchef.service.FellowChefRecipeService
import android.com.fellowchef.ui.BaseViewModel
import android.com.fellowchef.ui.recipe.Recipe
import android.com.fellowchef.util.RecipeType
import android.com.fellowchef.util.filterRecipesByTag
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

@HomeRecipeActivityScope
class HomeViewModel @Inject constructor(applicationContext: Context) : BaseViewModel(){

    private val repository: RecipeRepository =
            RecipeRepository(
                    Retrofit.Builder().baseUrl(BASE_URL)
                            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().add(KotlinJsonAdapterFactory()).build()))
                            .build()
                            .create(FellowChefRecipeService::class.java),
                    RecipeDatabase.getInstance(applicationContext).recipeDao(),
                    viewModelScope
            )

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _listOfRecipesBreakfast = MutableLiveData<List<Recipe>>()
    val listOfRecipesBreakfast : LiveData<List<Recipe>>
        get() = _listOfRecipesBreakfast

    private val _listOfRecipesTrending = MutableLiveData<List<Recipe>>()
    val listOfRecipesTrending : LiveData<List<Recipe>>
        get() = _listOfRecipesTrending

    val listOfRecipes = MediatorLiveData<Resource<List<Recipe>>>()

    init{
//        getRecipes()
        getRecipesData()
    }

    private fun getRecipesData() {
        val response = repository.getRecipesFeed()
        listOfRecipes.addSource(response){
            newData ->
            if (listOfRecipes.value != newData){
                listOfRecipes.value = newData
            }
        }
    }


    private fun getRecipes(){
//        viewModelScope.launch{
//            try{
//                Log.d("HomeViewModel", "Get Recipes.....")
//
//                val recipeList = FellowChefRecipeApi.retrofitService.getRecipes()
//                Log.d("HomeViewModel", "Recipe list retrieved : $recipeList")
//                _listOfRecipesBreakfast.value = filterRecipesByTag(recipeList, mutableListOf(RecipeType.LUNCH))
//                _listOfRecipesTrending.value = filterRecipesByTag(recipeList, mutableListOf(RecipeType.TRENDING))
//            }catch(e: Exception){
//                Log.d("HomeViewModel", "Error: ${e.message}")
//
//                isShowToast.value = true
//                toastErrorMessage.value = "Could not get recipes : ${e.message}"
//            }
//        }
    }
}