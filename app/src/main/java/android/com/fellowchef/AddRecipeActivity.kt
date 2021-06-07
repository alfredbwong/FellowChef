package android.com.fellowchef

import android.com.fellowchef.databinding.ActivityAddRecipeBinding
import android.com.fellowchef.di.AddRecipeComponent
import android.com.fellowchef.di.DaggerAddRecipeComponent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil

class AddRecipeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddRecipeBinding
    // Instance of the AppComponent that will be used by all the Activities in the project
    val addRecipeComponent: AddRecipeComponent by lazy {
        DaggerAddRecipeComponent.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_recipe)



    }
    companion object{
        const val TAG = "AddRecipeActivity"
    }
}