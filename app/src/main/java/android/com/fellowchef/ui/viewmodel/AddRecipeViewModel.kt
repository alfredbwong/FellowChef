package android.com.fellowchef.ui.viewmodel

import android.com.fellowchef.ui.recipe.Recipe
import javax.inject.Inject

class AddRecipeViewModel @Inject constructor(){

    val testString = "Alfred is here"
    var recipe = Recipe(0, "", "", "","")

    fun reset(){
        resetModel()
    }

    private fun resetModel(){
        recipe = Recipe(0,
            "",
            "",
            "",
            "")
    }


}