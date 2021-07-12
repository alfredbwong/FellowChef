package android.com.fellowchef.ui.search

import android.com.fellowchef.repository.RecipeRepository
import android.com.fellowchef.repository.models.Resource
import android.com.fellowchef.ui.BaseViewModel
import android.com.fellowchef.ui.recipe.Recipe
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(var repository: RecipeRepository) :
    BaseViewModel() {

    val listOfRecipeSearchResult = MediatorLiveData<Resource<List<Recipe>>>()

    fun getRecipeSearchResult(searchCategoryField: String) {
        val response = repository.getRecipesByFilter(
            viewModelScope,
            searchCategoryField
        )
        listOfRecipeSearchResult.addSource(response) { newData ->
            if (listOfRecipeSearchResult.value != newData) {
                listOfRecipeSearchResult.value = newData
            }
        }

    }
}

