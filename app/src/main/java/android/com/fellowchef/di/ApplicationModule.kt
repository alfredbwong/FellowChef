package android.com.fellowchef.di

import android.com.fellowchef.repository.BasicRecipeRepository
import android.com.fellowchef.repository.RecipeRepository
import android.com.fellowchef.service.FellowChefRecipeService
import android.com.fellowchef.service.FellowChefRecipeServiceHelper
import android.com.fellowchef.service.FellowChefRecipeServiceHelperImpl
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {




}