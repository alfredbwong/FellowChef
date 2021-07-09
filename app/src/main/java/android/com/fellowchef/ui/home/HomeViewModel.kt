package android.com.fellowchef.ui.home

import android.com.fellowchef.repository.RecipeRepository
import android.com.fellowchef.repository.models.Resource
import android.com.fellowchef.ui.BaseViewModel
import android.com.fellowchef.ui.recipe.Recipe
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor( var repository: RecipeRepository) : BaseViewModel(){

    private val viewModelJob = Job()
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
        getRecipesData()
    }

    private fun getRecipesData() {
        viewModelScope
            val response = repository.getRecipesFeed(viewModelScope)
            listOfRecipes.addSource(response){
                    newData ->
                if (listOfRecipes.value != newData){
                    listOfRecipes.value = newData
                }
            }

    }
}