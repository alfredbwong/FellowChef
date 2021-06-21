package android.com.fellowchef.ui.home

import android.com.fellowchef.R
import android.com.fellowchef.service.FellowChefRecipeApi
import android.com.fellowchef.service.FellowChefRecipeService
import android.com.fellowchef.ui.recipe.Recipe
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _listOfRecipes = MutableLiveData<List<Recipe>>()
    val listOfRecipes : LiveData<List<Recipe>>
        get() = _listOfRecipes

    private val _response = MutableLiveData<String>()
    val response : LiveData<String>
        get() = _response

    init{
        getRecipes()
    }

    private fun getRecipes(){
        viewModelScope.launch{
            try{
                val recipeList = FellowChefRecipeApi.retrofitService.getRecipes()
                _response.value =
                        "Success: ${recipeList.size} Recipe retrieved"
            }catch(e: Exception){
                _response.value = "Failure: ${e.message}"
            }
        }
    }
}