package android.com.fellowchef.ui.home

import android.com.fellowchef.di.HomeRecipeActivityScope
import android.com.fellowchef.service.FellowChefRecipeApi
import android.com.fellowchef.ui.BaseViewModel
import android.com.fellowchef.ui.recipe.Recipe
import android.com.fellowchef.util.RecipeType
import android.com.fellowchef.util.filterRecipesByTag
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HomeRecipeActivityScope
class HomeViewModel @Inject constructor() : BaseViewModel(){

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

    init{
        getRecipes()
    }

    private fun getRecipes(){
        viewModelScope.launch{
            try{
                Log.d("HomeViewModel", "Get Recipes.....")
                val recipeList = FellowChefRecipeApi.retrofitService.getRecipes()
                Log.d("HomeViewModel", "Recipe list retrieved : $recipeList")
                _listOfRecipesBreakfast.value = filterRecipesByTag(recipeList, mutableListOf(RecipeType.LUNCH))
                _listOfRecipesTrending.value = filterRecipesByTag(recipeList, mutableListOf(RecipeType.TRENDING))
            }catch(e: Exception){
                Log.d("HomeViewModel", "Error: ${e.message}")

                isShowToast.value = true
                toastErrorMessage.value = "Could not get recipes : ${e.message}"
            }
        }
    }
}