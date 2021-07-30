package android.com.fellowchef.repository

import android.com.fellowchef.database.model.RecipeCategory
import android.com.fellowchef.models.Ingredient
import android.com.fellowchef.models.Instruction
import android.com.fellowchef.ui.recipe.Recipe
import io.reactivex.Single
import javax.inject.Inject

@Deprecated(message="This is to test with Hilt not in test classes")
class FakeRecipeRepositoryProxy @Inject constructor(): BasicRecipeRepository{
    override fun getRecipesFeedFromNetwork(): Single<List<Recipe>> {
        return Single.just(mutableListOf(Recipe(1,"recipe1","recipeImageUrl1",
            mutableListOf("BREAKFAST"),"shortDesc1", "longDesc1", mutableListOf(Instruction(1,"instruction1")),
            mutableListOf(Ingredient("name1","cup",2F)), 2,1,1)))
    }

    override fun getListOfLikedRecipes(): Single<List<Recipe>> {
        return Single.just(mutableListOf(Recipe(1,"recipe1","recipeImageUrl1",
            mutableListOf("BREAKFAST"),"shortDesc1", "longDesc1", mutableListOf(Instruction(1,"instruction1")),
            mutableListOf(Ingredient("name1","cup",2F)), 2,1,1)))
    }

    override fun removeRecipeFromLiked(recipeId: Int): Single<Int> {
        return Single.just(1)
    }

    override fun addRecipeToLiked(recipe: Recipe): Single<Long> {
        return Single.just(1)
    }

    override fun getRecipeFiltersFeed(): Single<List<RecipeCategory>> {
        return Single.just(mutableListOf(RecipeCategory("CategoryName1", mutableListOf("field1"))))
    }

    override fun getListOfRecipesFiltered(filter: String): Single<List<Recipe>> {
        return Single.just(mutableListOf(Recipe(1,"recipe1","recipeImageUrl1",
            mutableListOf("BREAKFAST"),"shortDesc1", "longDesc1", mutableListOf(Instruction(1,"instruction1")),
            mutableListOf(Ingredient("name1","cup",2F)), 2,1,1)))
    }
}