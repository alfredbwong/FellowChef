package android.com.fellowchef.di

import android.com.fellowchef.database.RecipeDatabase
import android.com.fellowchef.repository.RecipeRepository
import android.com.fellowchef.service.FellowChefRecipeService
import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

//@Module
//@InstallIn(SingletonComponent::class)
object RepositoryModule {

//    @Provides
//    fun provideRecipeRepository(@ApplicationContext applicationContext: Context) : RecipeRepository {
//        val BASE_URL = "https://aqueous-scrubland-10484.herokuapp.com"
//
//        return RecipeRepository(
//            Retrofit.Builder().baseUrl(BASE_URL)
//                .addConverterFactory(
//                    MoshiConverterFactory.create(
//                        Moshi.Builder().add(
//                            KotlinJsonAdapterFactory()
//                        ).build()))
//                .build()
//                .create(FellowChefRecipeService::class.java),
//            RecipeDatabase.getInstance(applicationContext).recipeDao()
//        )
//    }
}