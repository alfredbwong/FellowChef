package android.com.fellowchef.di

import android.com.fellowchef.*
import android.com.fellowchef.ui.home.HomeFragment
import android.com.fellowchef.ui.search.SearchFragment
import dagger.Subcomponent

@MainRecipeActivityScope
@Subcomponent
interface MainRecipeComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create(): MainRecipeComponent
    }
    fun inject(activity: MainActivity)
    fun inject(fragment: HomeFragment)
    fun inject(fragment: RecipeDetailFragment)
    fun inject(fragment: SearchFragment)
}