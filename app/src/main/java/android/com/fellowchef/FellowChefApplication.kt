package android.com.fellowchef

import android.app.Application
import android.com.fellowchef.di.AppComponent
import android.com.fellowchef.di.DaggerAppComponent

class FellowChefApplication : Application() {

    val appComponent: AppComponent by lazy {
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        DaggerAppComponent.factory().create(applicationContext)
    }
}