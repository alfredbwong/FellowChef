package android.com.fellowchef.service

import android.com.fellowchef.database.model.RecipeCategory
import android.com.fellowchef.ui.recipe.Recipe
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject


interface FellowChefRecipeService {
    @GET("/api/json/v1/recipes")
    fun getRecipes(): Single<List<Recipe>>

    @GET("/api/json/v1/recipeFilters")
    fun getRecipeFilters(): Single<List<RecipeCategory>>

    @GET("/api/json/v1/recipesByFilter")
    fun getRecipesByFilter(@Query("filter") filter: String?): Single<List<Recipe>>
}

interface FellowChefRecipeServiceHelper{
    fun getRecipes(): Single<List<Recipe>>

    fun getRecipeFilters(): Single<List<RecipeCategory>>
}

class FellowChefRecipeServiceHelperImpl @Inject constructor(private val apiService: FellowChefRecipeService) : FellowChefRecipeServiceHelper{
    override fun getRecipes(): Single<List<Recipe>> = apiService.getRecipes()

    override fun getRecipeFilters(): Single<List<RecipeCategory>> = apiService.getRecipeFilters()

}


