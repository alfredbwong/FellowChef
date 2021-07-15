package android.com.fellowchef.database.converters

import android.com.fellowchef.models.Ingredient
import android.com.fellowchef.models.Instruction
import android.util.Log
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class InstructionTypeConverter {

    @TypeConverter
    fun toListOfInstructionsFromString(dataString: String) : List<Instruction>{
        val itemType = object: TypeToken<List<Instruction>>(){}.type
        return Gson().fromJson(dataString, itemType)
    }
    @TypeConverter
    fun toStringFromListOfInstructions(listInstructions: List<Instruction>) : String{
        return Gson().toJson(listInstructions)
    }
}