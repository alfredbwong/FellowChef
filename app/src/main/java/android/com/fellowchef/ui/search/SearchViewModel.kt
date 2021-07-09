package android.com.fellowchef.ui.search

import android.com.fellowchef.database.RecipeDatabase
import android.com.fellowchef.database.model.RecipeCategory
import android.com.fellowchef.repository.RecipeRepository
import android.com.fellowchef.repository.models.Resource
import android.com.fellowchef.service.BASE_URL
import android.com.fellowchef.service.FellowChefRecipeService
import android.com.fellowchef.ui.BaseViewModel
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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