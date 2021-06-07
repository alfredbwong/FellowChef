package android.com.fellowchef

import android.com.fellowchef.databinding.ActivityAddRecipeBinding
import android.com.fellowchef.di.AddRecipeComponent
import android.com.fellowchef.ui.viewmodel.AddRecipeViewModel
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import javax.inject.Inject

class AddRecipeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddRecipeBinding

    lateinit var addRecipeComponent: AddRecipeComponent

    @Inject
    lateinit var addRecipeViewModel : AddRecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        addRecipeComponent = (application as FellowChefApplication).appComponent.addRecipeComponent().create()
        addRecipeComponent.inject(this)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_recipe)



    }
    companion object{
        const val TAG = "AddRecipeActivity"
    }
}