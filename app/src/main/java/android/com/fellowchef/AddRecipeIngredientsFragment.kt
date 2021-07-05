package android.com.fellowchef

import android.com.fellowchef.databinding.FragmentAddRecipeIngredientsBinding
import android.com.fellowchef.ui.AddRecipeIngredientListAdapter
import android.com.fellowchef.ui.viewmodel.AddRecipeViewModel
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isNotEmpty
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [AddRecipeIngredientsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddRecipeIngredientsFragment : Fragment() {
    private lateinit var binding: FragmentAddRecipeIngredientsBinding

    @Inject
    lateinit var addRecipeViewModel: AddRecipeViewModel

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

        //Adapter for list of Ingredients
        val adapterListIngredients = addRecipeViewModel.recipeIngredientList.value?.let {
            listOfIngredients ->
            AddRecipeIngredientListAdapter(listOfIngredients, requireContext()) { pos ->
                addRecipeViewModel.removeIngredientItem(pos)
            }
        }
        binding.listViewIngredients.adapter = adapterListIngredients

        binding.buttonAddIngredientNext.setOnClickListener{
            if (binding.listViewIngredients.isNotEmpty()) {
                val action = AddRecipeIngredientsFragmentDirections.actionAddRecipeIngredientsFragmentToAddRecipeInstructionsFragment()
                findNavController().navigate(action)
            } else {
                Toast.makeText(requireContext(), "You must add at least 1 ingredient to the recipe!", Toast.LENGTH_SHORT).show()
            }
        }
        binding.buttonAddIngredient.setOnClickListener{
            if (binding.inputAddIngredientName.text.isNotBlank() && binding.numberInputIngredient.text.isNotBlank()) {
                val number = binding.numberInputIngredient.text.toString()
                val measurementSize = binding.spinnerMeasurementSizes.selectedItem.toString()
                val ingredient = binding.inputAddIngredientName.text.toString()
                addRecipeViewModel.addIngredientToRecipeList(number, measurementSize, ingredient)
                adapterListIngredients?.notifyDataSetChanged()
            } else {
                Toast.makeText(requireContext(), "You need to fill in all fields!", Toast.LENGTH_SHORT).show()
            }

        }
        addRecipeViewModel.recipeIngredientList.observe(viewLifecycleOwner, Observer {
            _ ->
            adapterListIngredients?.notifyDataSetChanged()
        })

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as AddRecipeActivity).addRecipeComponent.inject(this)
    }

    companion object {
        const val TAG = "Add-Ingredient"
    }
}