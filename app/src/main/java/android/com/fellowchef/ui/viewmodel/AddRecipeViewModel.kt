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

    private var _recipeTitle = MutableLiveData<String>()
    val recipeTitle : LiveData<String>
        get() = _recipeTitle

    private var _recipeType = MutableLiveData<String>()
    val recipeType : LiveData<String>
        get() = _recipeType




}