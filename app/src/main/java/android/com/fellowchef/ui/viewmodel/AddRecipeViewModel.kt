package android.com.fellowchef.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class AddRecipeViewModel @Inject constructor(){
    fun updateAddRecipeNameAndType(recipeTitle: String, recipeType: String) {
        _recipeTitle.value = recipeTitle
        _recipeType.value = recipeType

    }

    fun addIngredientToRecipeList(number: String, measurementSize: String, ingredient: String) {
        val text = "$number $measurementSize $ingredient"
        _recipeIngredientList.value?.add(text)
        _recipeIngredientList.value = _recipeIngredientList.value
    }

    fun removeIngredientItem(pos: Int) {
        _recipeIngredientList.value?.removeAt(pos)
        _recipeIngredientList.value = _recipeIngredientList.value


    }

    fun addInstructionToList(instruction: String) {
        _recipeInstructionsList.value?.add(instruction)
        _recipeInstructionsList.value = _recipeInstructionsList.value
    }

    fun removeInstruction(position: Int) {
        _recipeInstructionsList.value?.removeAt(position)
        _recipeInstructionsList.value = _recipeInstructionsList.value
    }


    private var _recipeInstructionsList = MutableLiveData<MutableList<String>>()
    val recipeInstructionsList :LiveData<MutableList<String>>
        get() = _recipeInstructionsList

    private var _recipeIngredientList = MutableLiveData<MutableList<String>>()
    val recipeIngredientList : LiveData<MutableList<String>>
        get() = _recipeIngredientList

    private var _recipeTitle = MutableLiveData<String>()
    val recipeTitle : LiveData<String>
        get() = _recipeTitle

    private var _recipeType = MutableLiveData<String>()
    val recipeType : LiveData<String>
        get() = _recipeType

    init{
        _recipeIngredientList.value = mutableListOf()
        _recipeInstructionsList.value = mutableListOf()
    }


}