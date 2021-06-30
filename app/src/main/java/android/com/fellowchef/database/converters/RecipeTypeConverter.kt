package android.com.fellowchef.database.converters

import android.com.fellowchef.util.RecipeType
import android.os.Build
import android.text.TextUtils
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.util.*

class RecipeTypeConverter {

    @TypeConverter
    fun toListOfString (value : String) : List<String> {
        return value.split(",")
    }
    @RequiresApi(Build.VERSION_CODES.N)
    @TypeConverter
    fun fromListOfString (value : List<String>) :  String{
        val joiner = StringJoiner(",")
        value.forEach{
            string -> joiner.add(string)
        }
        return joiner.toString()
    }
}