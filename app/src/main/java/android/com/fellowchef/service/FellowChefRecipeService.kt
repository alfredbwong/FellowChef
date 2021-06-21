package android.com.fellowchef.service

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

    const val BASE_URL = "https://aqueous-scrubland-10484.herokuapp.com"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    interface FellowChefRecipeService {
        @GET("/api/json/v1/recipes")
        fun getRecipes():
                Call<String>
    }

    object FellowChefRecipeApi{
        val retrofitService : FellowChefRecipeService by lazy{
            retrofit.create(FellowChefRecipeService::class.java)
        }
    }

