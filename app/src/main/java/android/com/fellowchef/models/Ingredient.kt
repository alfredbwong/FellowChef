package android.com.fellowchef.models

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Ingredient(
        val name: String,
        val size: String,
        val amount: Float
) :Parcelable{
}