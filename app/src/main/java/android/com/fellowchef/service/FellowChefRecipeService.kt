package android.com.fellowchef.service

import android.com.fellowchef.database.model.RecipeCategory
import android.com.fellowchef.ui.recipe.Recipe
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject


interface FellowChefRecipeService {
    @GET("/api/json/v1/recipes")
    fun getRecipes(): Call<List<Recipe>>

    @GET("/api/json/v1/recipeFilters")
    fun getRecipeFilters(): Call<List<RecipeCategory>>

    @GET("/api/json/v1/recipesByFilter")
    fun getRecipesByFilter(@Query("filter") filter: String?): Call<List<Recipe>>
}

interface FellowChefRecipeServiceHelper{
    fun getRecipes(): Call<List<Recipe>>

    fun getRecipeFilters(): Call<List<RecipeCategory>>
}

class FellowChefRecipeServiceHelperImpl @Inject constructor(private val apiService: FellowChefRecipeService) : FellowChefRecipeServiceHelper{
    override fun getRecipes(): Call<List<Recipe>> = apiService.getRecipes()

    override fun getRecipeFilters(): Call<List<RecipeCategory>> = apiService.getRecipeFilters()

}


