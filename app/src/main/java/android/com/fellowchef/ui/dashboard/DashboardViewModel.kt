package android.com.fellowchef.ui.dashboard

import android.com.fellowchef.database.model.RecipesLiked
import android.com.fellowchef.repository.RecipeRepository
import android.com.fellowchef.repository.models.Resource
import android.com.fellowchef.ui.recipe.Recipe
import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(var repository: RecipeRepository) : ViewModel() {

    val likedRecipes = MediatorLiveData<Resource<List<Recipe>>>()


    init {
        getLocallyStoredLikedRecipeFeed()
    }

    fun getLocallyStoredLikedRecipeFeed() {
        viewModelScope.launch {
            val response = repository.getRecipesFeed(viewModelScope)
            val responseLikedRecipes = MutableLiveData(repository.getRecipeIdsLiked())

            likedRecipes.addSource(response) { newData ->
                if (likedRecipes.value != newData) {
                    if (newData.data != null) {
                        likedRecipes.value = Resource.success(newData.data.filter { recipe ->
                            responseLikedRecipes.value!!.contains(recipe.id)
                        })

                    }
                }
            }
            likedRecipes.addSource(responseLikedRecipes) {
                if (likedRecipes.value != null) {
                    if (likedRecipes.value!!.data != null) {
                        likedRecipes.value?.data?.filter { recipe ->
                            responseLikedRecipes.value!!.contains(recipe.id)
                        }
                    }
                }

            }
        }
    }

    companion object {
        const val TAG = "DashboardViewModel"
    }
}