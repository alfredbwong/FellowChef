package android.com.fellowchef.database

import android.com.fellowchef.database.converters.IngredientTypeConverter
import android.com.fellowchef.database.converters.InstructionTypeConverter
import android.com.fellowchef.database.converters.RecipeTypeConverter
import android.com.fellowchef.database.model.RecipeCategory
import android.com.fellowchef.ui.recipe.Recipe
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [Recipe::class, RecipeCategory::class], version = 8)
@TypeConverters(RecipeTypeConverter::class, IngredientTypeConverter::class, InstructionTypeConverter::class)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDAO

    companion object {
        private var instance: RecipeDatabase? = null

        fun getInstance(context: Context): RecipeDatabase {
            if (instance == null) {
                instance = Room
                        .databaseBuilder(
                                context.applicationContext,
                                RecipeDatabase::class.java,
                                "recipe-db"
                        )
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return instance!!
        }
    }
}