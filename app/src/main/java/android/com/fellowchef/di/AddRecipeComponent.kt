package android.com.fellowchef.di

import android.com.fellowchef.AddRecipeActivity
import android.com.fellowchef.AddRecipeDetailFragment
import android.com.fellowchef.AddRecipeIngredientsFragment
import android.com.fellowchef.AddRecipeInstructionsFragment
import dagger.Subcomponent


@AddRecipeActivityScope
@Subcomponent
interface AddRecipeComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create(): AddRecipeComponent
    }
    fun inject(activity: AddRecipeActivity)
    fun inject(fragment: AddRecipeDetailFragment)
    fun inject(fragment: AddRecipeIngredientsFragment)
    fun inject(fragment: AddRecipeInstructionsFragment)
}