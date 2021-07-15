package android.com.fellowchef.ui.search

import android.com.fellowchef.database.model.RecipeCategory
import android.com.fellowchef.repository.RecipeRepository
import android.com.fellowchef.repository.models.Resource
import android.com.fellowchef.repository.models.Success
import android.com.fellowchef.ui.BaseViewModel
import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(var repository: RecipeRepository) : BaseViewModel() {

    val listOfRecipeFilters = MediatorLiveData<Resource<List<RecipeCategory>>>()

    val compositeDisposable by lazy { CompositeDisposable() }

    init {
        getRecipeFilters()
    }

    private fun getRecipeFilters() {
        compositeDisposable.add(repository.getRecipeFiltersFeed()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ listOfRecipeCategories ->
                listOfRecipeFilters.value = Resource.success(listOfRecipeCategories)
            }, { err ->
                listOfRecipeFilters.value = err.message?.let { it -> Resource.error(it, null) }
            }
            ))

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    companion object {
        const val TAG = "SearchViewModel"
    }
}