package android.com.fellowchef.di

import android.com.fellowchef.repository.BasicRecipeRepository
import android.com.fellowchef.repository.RecipeRepository
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

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


    @Module
    @InstallIn(SingletonComponent::class)
    interface Declarations{
        @Binds
        fun bindRecipeRepository(repository: RecipeRepository) : BasicRecipeRepository
    }
}