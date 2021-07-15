package android.com.fellowchef.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

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