package android.com.fellowchef.ui.recipe

import android.com.fellowchef.models.Ingredients
import android.com.fellowchef.models.Instruction
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Entity(tableName="recipe_table")
@JsonClass(generateAdapter = true)
@Parcelize
data class Recipe (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name="title")
    val title: String,
    @ColumnInfo(name="imageUrl")
    @Json(name="image_url")
    val imageUrl: String,
    @ColumnInfo(name="tags")
    @Json(name="tags")
    val recipeType : List<String>,
    @ColumnInfo(name="shortDescription")
    @Json(name="short_description")
    val shortDescription: String,
    @ColumnInfo(name="longDescription")
    @Json(name="long_description")
    val longDescription: String,
    @ColumnInfo(name="instructions")
    val instructions : List<Instruction>,
    @ColumnInfo(name="ingredients")
    val ingredients : List<Ingredients>,
    @ColumnInfo(name="numServings")
    @Json(name="num_servings")
    val numServings : Int,
    @ColumnInfo(name="numLikes")
    @Json(name="num_likes")
    val numLikes: Int,
    @ColumnInfo(name="numDislikes")
    @Json(name="num_dislikes")
    val numDislikes: Int,
) :Parcelable{

}