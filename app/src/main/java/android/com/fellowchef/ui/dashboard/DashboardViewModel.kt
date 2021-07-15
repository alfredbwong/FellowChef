package android.com.fellowchef.ui.dashboard

import android.com.fellowchef.database.model.RecipesLiked
import android.com.fellowchef.repository.RecipeRepository
import android.com.fellowchef.repository.models.Resource
import android.com.fellowchef.ui.recipe.Recipe
import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(var repository: RecipeRepository) : ViewModel() {


    private var _listOfLikedRecipes = MutableLiveData<Resource<List<Recipe>>>()
    val listOfLikedRecipes: LiveData<Resource<List<Recipe>>>
        get() = _listOfLikedRecipes

    private val compositeDisposable by lazy { CompositeDisposable() }

    init {
        getLocallyStoredLikedRecipeFeed()
    }

    private fun getLocallyStoredLikedRecipeFeed() {

        compositeDisposable.add(repository.getListOfLikedRecipes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { _ ->
                    _listOfLikedRecipes.value = Resource.loading(null)
                }
                .subscribe({ recipesList ->
                    _listOfLikedRecipes.value = Resource.success(recipesList)
                }, { err ->
                    _listOfLikedRecipes.value = err.message?.let { Resource.error(it, null) }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    companion object {
        const val TAG = "DashboardViewModel"
    }
}