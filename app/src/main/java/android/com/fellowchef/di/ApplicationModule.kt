package android.com.fellowchef.di

import android.com.fellowchef.database.RecipeDAO
import android.com.fellowchef.database.RecipeDatabase
import android.com.fellowchef.repository.BasicRecipeRepository
import android.com.fellowchef.repository.FakeRecipeRepositoryProxy
import android.com.fellowchef.repository.RecipeRepository
import android.com.fellowchef.service.FellowChefRecipeService
import android.com.fellowchef.service.FellowChefRecipeServiceHelper
import android.com.fellowchef.service.FellowChefRecipeServiceHelperImpl
import android.com.fellowchef.ui.recipe.Recipe
import android.content.Context
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    fun provideBaseUrl() = "https://aqueous-scrubland-10484.herokuapp.com"

    @Provides
    fun provideMoshi(): Moshi? = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun provideRetrofit(BASE_URL: String, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(BASE_URL: String): FellowChefRecipeService =
        Retrofit.Builder().baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().add(KotlinJsonAdapterFactory()).build()))
            .build()
            .create(FellowChefRecipeService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: FellowChefRecipeServiceHelperImpl): FellowChefRecipeServiceHelper =
        apiHelper


    @Provides
    @Singleton
    fun provideRecipeDAO(@ApplicationContext applicationContext: Context): RecipeDAO =
        RecipeDatabase.getInstance(applicationContext).recipeDao()

    @Module
    @InstallIn(SingletonComponent::class)
    interface Declarations{
        @Binds
        fun bindRecipeRepository(repository: RecipeRepository) : BasicRecipeRepository
    }


}