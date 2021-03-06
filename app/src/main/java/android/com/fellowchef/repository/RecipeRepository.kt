package android.com.fellowchef.repository

import android.com.fellowchef.database.RecipeDAO
import android.com.fellowchef.database.model.RecipeCategory
import android.com.fellowchef.repository.models.*
import android.com.fellowchef.service.FellowChefRecipeService
import android.com.fellowchef.ui.recipe.Recipe
import android.com.fellowchef.util.safeExecute
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class RecipeRepository @Inject constructor(
        private val apiService: FellowChefRecipeService,
        private val recipeDAO: RecipeDAO
) : BasicRecipeRepository{



    override fun getRecipesFeedFromNetwork(): Single<List<Recipe>> {
        return apiService.getRecipes()
    }

    override fun getListOfLikedRecipes(): Single<List<Recipe>> {
        return recipeDAO.getLikedRecipes()
    }

    override fun removeRecipeFromLiked(recipeId : Int): Single<Int> {
        return recipeDAO.removeRecipeFromLiked(recipeId)

    }

    override fun addRecipeToLiked(recipe: Recipe): Single<Long> {
        return recipeDAO.addToLikedRecipes(recipe)

    }

    override fun getRecipeFiltersFeed(): Single<List<RecipeCategory>> {
        return apiService.getRecipeFilters()
    }

    override fun getListOfRecipesFiltered(filter: String): Single<List<Recipe>> {
        return apiService.getRecipesByFilter(filter)
    }

    companion object {
        const val TAG = "RecipeRepository"
    }
}