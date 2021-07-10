package android.com.fellowchef.database.converters

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.util.*

class RecipeIdListTypeConverter {
    @RequiresApi(Build.VERSION_CODES.N)
    @TypeConverter
    fun toStringFromListInt(value: List<Int>): String {
        val joiner = StringJoiner(",")
        value.forEach { recipeId ->
            joiner.add(recipeId.toString())
        }
        return joiner.toString()
    }
    @TypeConverter
    fun toListIntFromString (value : String) :  List<Int>{
        return value.split(",").map{
            it-> it.toInt()
        }.toMutableList()

    }
}