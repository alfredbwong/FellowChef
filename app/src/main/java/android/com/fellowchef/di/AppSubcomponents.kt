package android.com.fellowchef.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module(subcomponents = [AddRecipeComponent::class])
class AppSubcomponents {
}