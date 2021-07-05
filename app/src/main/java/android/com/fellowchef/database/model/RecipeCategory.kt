package android.com.fellowchef.database.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Entity(tableName = "recipe_category")
@JsonClass(generateAdapter = true)
@Parcelize
data class RecipeCategory(
        @PrimaryKey
        @Json(name="category_name")
        val categoryName: String,
        @ColumnInfo(name = "category_fields")
        @Json(name = "category_fields")
        val categoryFields : List<String>) : Parcelable {
}
