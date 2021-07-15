package android.com.fellowchef.database.converters

import android.com.fellowchef.models.Instruction
import android.com.fellowchef.util.RecipeType
import android.os.Build
import android.text.TextUtils
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class RecipeTypeConverter {

    @TypeConverter
    fun toListOfString (value : String) : List<String> {
        val itemType = object: TypeToken<List<String>>(){}.type
        return Gson().fromJson(value, itemType)
    }
    @TypeConverter
    fun fromListOfString (value : List<String>) :  String{
        return Gson().toJson(value)
    }
}