package android.com.fellowchef.di

import android.com.fellowchef.database.RecipeDAO
import android.com.fellowchef.database.RecipeDatabase
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideRecipeDAO(@ApplicationContext applicationContext: Context): RecipeDAO =
        RecipeDatabase.getInstance(applicationContext).recipeDao()
}