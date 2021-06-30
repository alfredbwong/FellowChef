package android.com.fellowchef.service

import android.com.fellowchef.ui.recipe.Recipe
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://aqueous-scrubland-10484.herokuapp.com"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface FellowChefRecipeService {
    @GET("/api/json/v1/recipes")
    suspend fun getRecipes():List<Recipe>
}

object FellowChefRecipeApi {
    val retrofitService: FellowChefRecipeService by lazy {
        retrofit.create(FellowChefRecipeService::class.java)
    }
}

