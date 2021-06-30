package android.com.fellowchef.database.converters

import android.com.fellowchef.models.Ingredient
import androidx.room.TypeConverter
import java.lang.StringBuilder

class IngredientTypeConverter {

    @TypeConverter
    fun toListOfIngredientsFromString(dataString: String) : List<Ingredient>{
        val ingredientsRaw = dataString.split("!!!")
        val ingredientListResult = mutableListOf<Ingredient>()
        ingredientsRaw.forEach {
            ingredientStr ->
            if (ingredientStr.isNotEmpty()){
                val ingredientProperties = ingredientStr.split("//")
                val ingredient = Ingredient(ingredientProperties[0], ingredientProperties[1], ingredientProperties[2].toFloat())
                ingredientListResult.add(ingredient)
            }

        }

        return ingredientListResult
    }
    @TypeConverter
    fun toStringFromListOfIngredients(listIngredients: List<Ingredient>) : String{
        val stringResult = StringBuilder()
        listIngredients.forEach {
            ingredient ->
            stringResult.append(ingredient.name)
                    .append("//")
                    .append(ingredient.size)
                    .append("//")
                    .append(ingredient.amount)
                    .append("!!!")
        }
        return stringResult.toString()
    }

}