package android.com.fellowchef.database.converters

import android.com.fellowchef.models.Instruction
import android.util.Log
import androidx.room.TypeConverter

class InstructionTypeConverter {

    @TypeConverter
    fun toListOfInstructionsFromString(dataString: String) : List<Instruction>{
        val instructionRaw = dataString.split("!!!")
        val instructionListResult = mutableListOf<Instruction>()
        instructionRaw.forEach {
            instructionStr ->
            if (instructionStr.isNotEmpty()){
                val instructionStrProperties = instructionStr.split("//")
                val instruction = Instruction(instructionStrProperties[0].toInt(), instructionStrProperties[1])
                instructionListResult.add(instruction)
            }
        }

        return instructionListResult
    }
    @TypeConverter
    fun toStringFromListOfInstructions(listInstructions: List<Instruction>) : String{
        val stringResult = StringBuilder()
        listInstructions.forEach {
            instruction ->
            stringResult.append(instruction.step)
                    .append("//")
                    .append(instruction.text)
                    .append("!!!")
        }
        return stringResult.toString()
    }
}