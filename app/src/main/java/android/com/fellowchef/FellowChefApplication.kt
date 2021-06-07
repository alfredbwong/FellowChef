package android.com.fellowchef

import android.app.Application
import android.com.fellowchef.di.AppComponent
import android.com.fellowchef.di.DaggerAppComponent

class FellowChefApplication : Application() {

    // Instance of the AppComponent that will be used by all the Activities in the project
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.create()
    }
}