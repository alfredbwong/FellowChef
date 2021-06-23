package android.com.fellowchef.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Ingredients(
        val name: String,
        val amount: Integer
) {
}