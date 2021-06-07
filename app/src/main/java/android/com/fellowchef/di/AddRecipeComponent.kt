package android.com.fellowchef.di

import android.com.fellowchef.AddRecipeActivity
import android.com.fellowchef.AddRecipeDetailFragment
import dagger.Component


@Component
interface AddRecipeComponent {
    fun inject(activity: AddRecipeActivity)
    fun inject(fragment: AddRecipeDetailFragment)
}