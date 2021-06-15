package android.com.fellowchef.ui.recipe

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="recipe_table")
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name="title")
    var title: String,
    @ColumnInfo(name="imageUrl")
    val imageUrl: String,
    @ColumnInfo(name="recipeType")
    var recipeType : String,
    @ColumnInfo(name="shortDescription")
    val shortDescription: String,
    @ColumnInfo(name="longDescription")
    val longDescription: String,
    @ColumnInfo(name="ingredients")
    val ingredients: String,
    @ColumnInfo(name="instructions")
    val instructions: String
) {

}