package android.com.fellowchef

import android.com.fellowchef.di.AddRecipeComponent
import android.com.fellowchef.di.HomeRecipeComponent
import android.com.fellowchef.ui.home.HomeViewModel
import android.com.fellowchef.ui.viewmodel.MainViewModel
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var homeViewModel: HomeViewModel

    lateinit var homeRecipeComponent: HomeRecipeComponent


    override fun onCreate(savedInstanceState: Bundle?) {
        //Use Dagger to inject dependencies

        homeRecipeComponent = (applicationContext as FellowChefApplication).appComponent.homeRecipeComponent().create()
        homeRecipeComponent.inject(this)


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}