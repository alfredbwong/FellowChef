package android.com.fellowchef.ui.viewmodel

import android.com.fellowchef.di.AddRecipeActivityScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

@AddRecipeActivityScope
class AddRecipeViewModel @Inject constructor(){
    fun updateAddRecipeNameAndType(recipeTitle: String, recipeType: String) {
        _recipeTitle.value = recipeTitle
        _recipeType.value = recipeType

    }

    fun addIngredientToRecipeList(number: String, measurementSize: String, ingredient: String) {
        val text = "$number $measurementSize $ingredient"
        _recipeIngredientList.value?.add(text)
    }

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
    }


}