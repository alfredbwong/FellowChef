package android.com.fellowchef.ui.home

import android.com.fellowchef.repository.RecipeRepository
import android.com.fellowchef.repository.models.Failure
import android.com.fellowchef.repository.models.Resource
import android.com.fellowchef.repository.models.Response
import android.com.fellowchef.repository.models.Success
import android.com.fellowchef.ui.BaseViewModel
import android.com.fellowchef.ui.recipe.Recipe
import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Job
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(var repository: RecipeRepository) : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private var _listOfRecipes = MutableLiveData<Resource<List<Recipe>>>()
    val listOfRecipes: LiveData<Resource<List<Recipe>>>
        get() = _listOfRecipes

    private var _error = MutableLiveData<Response<String>>()
    val error: LiveData<Response<String>>
        get() = _error


    private val compositeDisposable by lazy { CompositeDisposable() }

    init {
        getRecipesData()
    }

    private fun getRecipesData() {
        compositeDisposable.add(repository.getRecipesFeedFromNetwork()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _ ->
                _listOfRecipes.value = Resource.loading(null)
            }
            .subscribe({ recipes ->
                if (recipes != null && recipes.isNotEmpty()) {
                    _listOfRecipes.value = Resource.success(recipes)
                }
            }, { err ->
                _listOfRecipes.value = err.message?.let { Resource.error(it, null) }

            })
        )
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    companion object {
        const val TAG = "HomeViewModel"
    }
}