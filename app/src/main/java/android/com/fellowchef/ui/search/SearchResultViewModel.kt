package android.com.fellowchef.ui.search

import android.com.fellowchef.repository.RecipeRepository
import android.com.fellowchef.repository.models.Resource
import android.com.fellowchef.ui.BaseViewModel
import android.com.fellowchef.ui.recipe.Recipe
import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(var repository: RecipeRepository) :
    ViewModel() {

    private val recipeFilterArg = MutableLiveData<String>()

    private val _listOfRecipeSearchResult = MutableLiveData<Resource<List<Recipe>>>()
    val listOfRecipeSearchResult: LiveData<Resource<List<Recipe>>>
        get() = _listOfRecipeSearchResult

    private val compositeDisposable by lazy { CompositeDisposable() }

    fun getRecipeSearchResult() {
        Log.d(TAG, "recipeFilterArg.value ${recipeFilterArg.value}")
        compositeDisposable.add(repository.getListOfRecipesFiltered(recipeFilterArg.value!!.toUpperCase()!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _ ->
                _listOfRecipeSearchResult.value = Resource.loading(null)
            }
            .subscribe({ listRecipes ->
                _listOfRecipeSearchResult.value = Resource.success(listRecipes)
            }, { err ->
                _listOfRecipeSearchResult.value =Resource.error( "error getting recipes by filter", null)
            })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun updateReferenceFilter(searchCategoryField: String) {
        recipeFilterArg.value = searchCategoryField
    }
    companion object{
        const val TAG = "SearchResultVM"
    }
}

