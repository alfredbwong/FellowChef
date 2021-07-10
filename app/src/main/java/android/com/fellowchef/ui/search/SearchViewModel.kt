package android.com.fellowchef.ui.search

import android.com.fellowchef.database.model.RecipeCategory
import android.com.fellowchef.repository.RecipeRepository
import android.com.fellowchef.repository.models.Resource
import android.com.fellowchef.ui.BaseViewModel
import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor( var repository: RecipeRepository) : BaseViewModel() {

    val listOfRecipeFilters = MediatorLiveData<Resource<List<RecipeCategory>>>()

    init {
        getRecipeFilters()
    }

    private fun getRecipeFilters() {

        val response = repository.getRecipeFiltersFeed(viewModelScope)
        listOfRecipeFilters.addSource(response){
            newData ->
            Log.i(TAG, "New data for filters ${newData.data}")
            if (listOfRecipeFilters.value != newData){
                listOfRecipeFilters.value = newData
            }
        }
    }
companion object{
    const val TAG = "SearchViewModel"
}
}