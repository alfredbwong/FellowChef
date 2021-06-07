package android.com.fellowchef

import android.com.fellowchef.databinding.FragmentAddRecipeIngredientsBinding
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController


/**
 * A simple [Fragment] subclass.
 * Use the [AddRecipeIngredientsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddRecipeIngredientsFragment : Fragment() {
    private lateinit var binding: FragmentAddRecipeIngredientsBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentAddRecipeIngredientsBinding.inflate(inflater, container ,false)
        val spinner = binding.spinnerMeasurementSizes
        ArrayAdapter.createFromResource(
            requireContext(), R.array.measurement_sizes_array, android.R.layout.simple_spinner_item
        ).apply {
            this.setDropDownViewResource((android.R.layout.simple_spinner_dropdown_item))
            spinner.adapter = this
        }
        binding.buttonAddIngredientNext.setOnClickListener{
            val action = AddRecipeIngredientsFragmentDirections.actionAddRecipeIngredientsFragmentToAddRecipeInstructionsFragment()
            findNavController().navigate(action)
        }
        binding.buttonAddIngredient.setOnClickListener{
            Log.i(TAG, "Clicked to add a ingredient...")
            
        }
        return binding.root
    }

    companion object {
        const val TAG = "Add-Ingredient"
    }
}