package android.com.fellowchef.database

import android.com.fellowchef.database.model.RecipeCategory
import android.com.fellowchef.ui.recipe.Recipe
import androidx.room.*
import io.reactivex.Observable
import io.reactivex.Single


@Dao
interface RecipeDAO {


    @Query("DELETE FROM recipe_table")
    suspend fun deleteAllRecipes()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipes(listOfRecipes : List<Recipe>) : List<Long>

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



    @Query("SELECT (recipe_ids) FROM recipe_liked_table")
    fun getRecipeIdsLiked(): Single<List<Int>>

    @Query("SELECT * FROM recipe_table WHERE tags LIKE :categoryField")
    suspend fun getRecipesByFilter(categoryField: String): List<Recipe>?

    @Query("SELECT * FROM recipe_table")
    fun getLikedRecipes(): Single<List<Recipe>>

    @Query("DELETE FROM recipe_table WHERE (id=:recipeId)")
    fun removeRecipeFromLiked(recipeId : Int) : Single<Integer>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun addToLikedRecipes(recipe:Recipe) : Single<Long>

}