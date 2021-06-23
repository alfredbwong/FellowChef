package android.com.fellowchef.di

import android.com.fellowchef.*
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppSubcomponents::class])
interface AppComponent {
    //Factory to create incstances of the AppComponent
    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context) : AppComponent
    }

    fun addRecipeComponent(): AddRecipeComponent.Factory
    fun homeRecipeComponent(): HomeRecipeComponent.Factory


}