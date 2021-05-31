package android.com.fellowchef.ui.home

import android.com.fellowchef.R
import android.com.fellowchef.ui.recipe.Recipe
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    val recipeList = mutableListOf<Recipe>()

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _listOfRecipes = MutableLiveData<List<Recipe>>()
    val listOfRecipes : LiveData<List<Recipe>>
        get() = _listOfRecipes

    init{
        addTestRecipe()
        _listOfRecipes.value = recipeList
    }

    private fun addTestRecipe(){
        val recipe1 = Recipe(1,"TestRecipe1", "https://upload.wikimedia.org/wikipedia/en/thumb/a/a6/Pok%C3%A9mon_Pikachu_art.png/220px-Pok%C3%A9mon_Pikachu_art.png", "Small Descipriton")
        recipeList.add(recipe1)
        val recipe2 = Recipe(2, "TestRecipe2", "https://upload.wikimedia.org/wikipedia/en/thumb/a/a6/Pok%C3%A9mon_Pikachu_art.png/220px-Pok%C3%A9mon_Pikachu_art.png", "Small Descipriton")
        recipeList.add(recipe2)
        val recipe3 = Recipe(3, "TestRecipe3", "https://upload.wikimedia.org/wikipedia/en/thumb/a/a6/Pok%C3%A9mon_Pikachu_art.png/220px-Pok%C3%A9mon_Pikachu_art.png", "Small Descipriton")
        recipeList.add(recipe3)
    }
}