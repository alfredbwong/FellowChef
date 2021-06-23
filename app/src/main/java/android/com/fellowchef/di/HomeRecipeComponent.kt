package android.com.fellowchef.di

import android.com.fellowchef.*
import android.com.fellowchef.ui.home.HomeFragment
import dagger.Subcomponent

@HomeRecipeActivityScope
@Subcomponent
interface HomeRecipeComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create(): HomeRecipeComponent
    }
    fun inject(activity: MainActivity)
    fun inject(fragment: HomeFragment)
    fun inject(fragment: RecipeDetailFragment)
}