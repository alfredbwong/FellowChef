package android.com.fellowchef.ui.recipe

import android.com.fellowchef.models.Ingredient
import android.com.fellowchef.models.Instruction
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Entity(tableName = "recipe_table")
@JsonClass(generateAdapter = true)
@Parcelize
data class Recipe(
        @PrimaryKey(autoGenerate = true)
        @Json(name = "id")
        val id: Int,
        @ColumnInfo(name = "title")
        @Json(name = "title")
        val title: String,
        @ColumnInfo(name = "imageUrl")
        @Json(name = "image_url")
        val imageUrl: String,
        @ColumnInfo(name = "tags")
        @Json(name = "tags")
        val recipeType: List<String>,
        @ColumnInfo(name = "shortDescription")
        @Json(name = "short_description")
        val shortDescription: String,
        @ColumnInfo(name = "longDescription")
        @Json(name = "long_description")
        val longDescription: String,
        @ColumnInfo(name = "instructions")
        @Json(name="instructions")
        val instructions: List<Instruction>,
        @ColumnInfo(name = "ingredients")
        @Json(name="ingredients")
        val ingredients: List<Ingredient>,
        @ColumnInfo(name = "numServings")
        @Json(name = "num_servings")
        val numServings: Int,
        @ColumnInfo(name = "numLikes")
        @Json(name = "num_likes")
        val numLikes: Int,
        @ColumnInfo(name = "numDislikes")
        @Json(name = "num_dislikes")
        val numDislikes: Int,
        @ColumnInfo(name = "is_liked")
        @Transient
        val isLiked: Boolean = false
) : Parcelable {


}
