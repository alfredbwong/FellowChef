package android.com.fellowchef.di

import android.com.fellowchef.service.FellowChefRecipeService
import android.com.fellowchef.service.FellowChefRecipeServiceHelper
import android.com.fellowchef.service.FellowChefRecipeServiceHelperImpl
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ServiceModule {
    @Provides
    fun provideBaseUrl() = "https://aqueous-scrubland-10484.herokuapp.com"

    @Provides
    @Singleton
    fun provideApiService(BASE_URL: String): FellowChefRecipeService =
        Retrofit.Builder().baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(
                        KotlinJsonAdapterFactory()
                    ).build()))
            .build()
            .create(FellowChefRecipeService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: FellowChefRecipeServiceHelperImpl): FellowChefRecipeServiceHelper =
        apiHelper
}