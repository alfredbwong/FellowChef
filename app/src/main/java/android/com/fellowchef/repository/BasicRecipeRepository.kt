package android.com.fellowchef.repository

import android.com.fellowchef.database.model.RecipeCategory
import android.com.fellowchef.ui.recipe.Recipe
import io.reactivex.Single

interface BasicRecipeRepository {
    fun getRecipesFeedFromNetwork(): Single<List<Recipe>>

    fun getListOfLikedRecipes(): Single<List<Recipe>>

    fun removeRecipeFromLiked(recipeId : Int): Single<Int>

    fun addRecipeToLiked(recipe: Recipe): Single<Long>

    fun getRecipeFiltersFeed(): Single<List<RecipeCategory>>

    fun getListOfRecipesFiltered(filter: String): Single<List<Recipe>>

}
