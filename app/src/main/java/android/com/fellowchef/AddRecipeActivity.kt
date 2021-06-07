package android.com.fellowchef

import android.com.fellowchef.databinding.ActivityAddRecipeBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil

class AddRecipeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddRecipeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_recipe)



    }
    companion object{
        const val TAG = "AddRecipeActivity"
    }
}