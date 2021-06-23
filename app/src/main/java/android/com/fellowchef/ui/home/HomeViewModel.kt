package android.com.fellowchef.ui.home

import android.com.fellowchef.R
import android.com.fellowchef.service.FellowChefRecipeApi
import android.com.fellowchef.service.FellowChefRecipeService
import android.com.fellowchef.ui.BaseViewModel
import android.com.fellowchef.ui.recipe.Recipe
import android.com.fellowchef.util.RecipeType
import android.com.fellowchef.util.filterRecipesByTag
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _listOfRecipesBreakfast = MutableLiveData<List<Recipe>>()
    val listOfRecipesBreakfast : LiveData<List<Recipe>>
        get() = _listOfRecipesBreakfast


    init{
        getRecipes()
    }

    private fun getRecipes(){
        viewModelScope.launch{
            try{
                Log.i("ViewModel", "Get Recipess.....")
                val recipeList = FellowChefRecipeApi.retrofitService.getRecipes()
                Log.i("ViewModel", "$recipeList")
                _listOfRecipesBreakfast.value = filterRecipesByTag(recipeList, mutableListOf(RecipeType.LUNCH))
            }catch(e: Exception){
                Log.i("ViewModel", "${e.message}")

                isShowToast.value = true
                toastErrorMessage.value = "Could not get recipes : ${e.message}"
            }
        }
    }
}