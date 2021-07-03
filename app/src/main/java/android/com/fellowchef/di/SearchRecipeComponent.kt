package android.com.fellowchef.di

import android.com.fellowchef.ui.search.SearchFragment
import dagger.Subcomponent

@SearchRecipeActivityScope
@Subcomponent
interface SearchRecipeComponent {
    @Subcomponent.Factory
    interface Factory{
        fun create(): SearchRecipeComponent
    }
}