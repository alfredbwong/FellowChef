package android.com.fellowchef.database.converters

import android.com.fellowchef.models.Ingredient
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.StringBuilder

class IngredientTypeConverter {

    @TypeConverter
    fun toListOfIngredientsFromString(dataString: String) : List<Ingredient>{
        val itemType = object: TypeToken<List<Ingredient>>(){}.type
        return Gson().fromJson(dataString, itemType)
    }
    @TypeConverter
    fun toStringFromListOfIngredients(listIngredients: List<Ingredient>) : String{
        return Gson().toJson(listIngredients)
    }

}