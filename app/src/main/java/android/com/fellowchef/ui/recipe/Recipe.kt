package android.com.fellowchef.ui.recipe

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName="recipe_table")
@JsonClass(generateAdapter = true)
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name="title")
    val title: String,
    @ColumnInfo(name="imageUrl")
    @Json(name="imageurl")
    val imageUrl: String,
    @ColumnInfo(name="recipeType")
    @Json(name="recipetype")
    val recipeType : List<String>,
    @ColumnInfo(name="shortDescription")
    @Json(name="shortdescription")
    val shortDescription: String,
    @ColumnInfo(name="longDescription")
    @Json(name="longdescription")
    val longDescription: String,
    @ColumnInfo(name="instructions")
    val instructions : List<String>,
    @ColumnInfo(name="ingredients")
    val ingredients : List<String>,
) {

}