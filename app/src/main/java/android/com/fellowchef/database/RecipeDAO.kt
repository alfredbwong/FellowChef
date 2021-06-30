package android.com.fellowchef.database

import android.com.fellowchef.ui.recipe.Recipe
import androidx.lifecycle.LiveData
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
}