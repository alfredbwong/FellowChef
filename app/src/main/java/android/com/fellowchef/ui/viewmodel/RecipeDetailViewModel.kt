package android.com.fellowchef.ui.viewmodel

import android.com.fellowchef.repository.RecipeRepository
import android.com.fellowchef.ui.recipe.Recipe
import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(var recipeRepository: RecipeRepository ): ViewModel() {

    var recipeReferenced = MediatorLiveData<Recipe>()
    val isRecipeLiked = MediatorLiveData<Boolean>()

    fun addOrRemoveLikedRecipe(recipeId: Int, recipeLiked: Boolean) {
        viewModelScope.launch {
            if (recipeLiked) {
                recipeRepository.addRecipeToLiked(recipeId)
            }else {
                recipeRepository.removeRecipeFromLiked(recipeId)
            }
        }

    }

    init{
        Log.i(TAG, "Init...")
        getIdsRecipeLiked()

    }

    private fun getIdsRecipeLiked() {
        viewModelScope.launch {
            val likeRecipeIds = recipeRepository.getRecipeIdsLiked()
            Log.i(TAG, "likeRecipeIds: $likeRecipeIds")
            if (recipeReferenced.value?.let { likeRecipeIds.contains(it.id) } == true){
                isRecipeLiked.value = true
            }
        }

    }

    fun updateReferencedRecipe(recipe: Recipe) {
        recipeReferenced.value = recipe
    }

    companion object{
        const val TAG = "RecipeDetailViewModel"
    }

}