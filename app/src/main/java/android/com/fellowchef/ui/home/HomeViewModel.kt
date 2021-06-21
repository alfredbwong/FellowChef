package android.com.fellowchef.ui.home

import android.com.fellowchef.R
import android.com.fellowchef.service.FellowChefRecipeApi
import android.com.fellowchef.service.FellowChefRecipeService
import android.com.fellowchef.ui.recipe.Recipe
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    val recipeList = mutableListOf<Recipe>()

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _listOfRecipes = MutableLiveData<List<Recipe>>()
    val listOfRecipes : LiveData<List<Recipe>>
        get() = _listOfRecipes

    private val _response = MutableLiveData<String>()
    val response : LiveData<String>
        get() = _response

    init{
        addTestRecipe()
        getRecipes()
        _listOfRecipes.value = recipeList
    }

    private fun addTestRecipe(){
        val recipe1 = Recipe(1,"TestRecipe1", "https://upload.wikimedia.org/wikipedia/en/thumb/a/a6/Pok%C3%A9mon_Pikachu_art.png/220px-Pok%C3%A9mon_Pikachu_art.png", "test","Small Descipriton", "Long Descipriton", "ingredient1", "instruction1")
        recipeList.add(recipe1)
        val recipe2 = Recipe(2, "TestRecipe2", "https://upload.wikimedia.org/wikipedia/en/thumb/a/a6/Pok%C3%A9mon_Pikachu_art.png/220px-Pok%C3%A9mon_Pikachu_art.png","test", "Small Descipriton","Long Descipriton","ingredient2", "instruction1")
        recipeList.add(recipe2)
        val recipe3 = Recipe(3, "TestRecipe3", "https://upload.wikimedia.org/wikipedia/en/thumb/a/a6/Pok%C3%A9mon_Pikachu_art.png/220px-Pok%C3%A9mon_Pikachu_art.png", "test","Small Descipriton","Long Descipriton","ingredient3", "instruction1")
        recipeList.add(recipe3)
        val recipe4 = Recipe(4, "TestRecipe4", "https://upload.wikimedia.org/wikipedia/en/thumb/a/a6/Pok%C3%A9mon_Pikachu_art.png/220px-Pok%C3%A9mon_Pikachu_art.png", "test","Small Descipriton","Long Descipriton","ingredient4", "instruction1")
        recipeList.add(recipe4)
        val recipe5 = Recipe(5, "TestRecipe5", "https://upload.wikimedia.org/wikipedia/en/thumb/a/a6/Pok%C3%A9mon_Pikachu_art.png/220px-Pok%C3%A9mon_Pikachu_art.png", "test","Small Descipriton","Long Descipriton","ingredient4", "instruction1")
        recipeList.add(recipe5)
        val recipe6 = Recipe(6, "TestRecipe6", "https://upload.wikimedia.org/wikipedia/en/thumb/a/a6/Pok%C3%A9mon_Pikachu_art.png/220px-Pok%C3%A9mon_Pikachu_art.png", "test","Small Descipriton","Long Descipriton","ingredient4", "instruction1")
        recipeList.add(recipe6)
    }

    private fun getRecipes(){
        FellowChefRecipeApi.retrofitService.getRecipes().enqueue(
            object: Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    _response.value = response.body()
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    _response.value = "Failure" + t.message
                }
            }
        )
    }
}