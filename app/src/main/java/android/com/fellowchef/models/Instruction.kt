package android.com.fellowchef.models

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Instruction (val step : Int, val text: String) : Parcelable {
}