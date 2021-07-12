package android.com.fellowchef.database

import android.com.fellowchef.database.model.RecipeCategory
import android.com.fellowchef.ui.recipe.Recipe
import androidx.room.*


@Dao
interface RecipeDAO {
    @Query("SELECT * FROM recipe_table")
    suspend fun getRecipes() : List<Recipe>

    @Query("DELETE FROM recipe_table")
    suspend fun deleteAllRecipes()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(listOfRecipes : List<Recipe>) : List<Long>

    @Transaction
    suspend fun updateData(data: List<Recipe>) : List<Long> {
        deleteAllRecipes()
        return insertRecipes(data)
    }

    @Query("SELECT * FROM recipe_category")
    suspend fun getRecipeFilters() : List<RecipeCategory>

    @Transaction
    suspend fun updateFilterData(data: List<RecipeCategory>) : List<Long> {
        deleteAllRecipeFilters()
        return insertRecipeFilters(data)
    }

    @Query("DELETE FROM recipe_category")
    suspend fun deleteAllRecipeFilters()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeFilters(listOfRecipeFilters : List<RecipeCategory>) : List<Long>

    @Query("INSERT INTO recipe_liked_table values (null,:recipeId)")
    suspend fun addToLikedRecipes(recipeId : Int) : Long

    @Query("DELETE FROM recipe_liked_table WHERE (recipe_ids=:recipeId)")
    suspend fun removeRecipeFromLiked(recipeId : Int)

    @Query("SELECT (recipe_ids) FROM recipe_liked_table")
    suspend fun getRecipeIdsLiked(): List<Int>

    @Query("SELECT * FROM recipe_table WHERE tags LIKE :categoryField")
    suspend fun getRecipesByFilter(categoryField: String): List<Recipe>?

}