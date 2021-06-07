package android.com.fellowchef

import android.com.fellowchef.databinding.FragmentAddRecipeDetailBinding
import android.com.fellowchef.ui.viewmodel.AddRecipeViewModel
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [AddRecipeDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddRecipeDetailFragment : Fragment() {
    private lateinit var binding: FragmentAddRecipeDetailBinding
    @Inject
    lateinit var addRecipeViewModel : AddRecipeViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentAddRecipeDetailBinding.inflate(inflater, container, false)
        binding.buttonNext.setOnClickListener{
            val recipeTitleInputted = binding.recipeNameInput.text.toString()
            val recipeTypeChecked = binding.recipeTypeRadioGroup.checkedRadioButtonId != -1
            if (recipeTitleInputted.isNotEmpty() && recipeTypeChecked) {
                addRecipeViewModel.recipe.title = binding.recipeNameInput.text.toString()
                addRecipeViewModel.recipe.recipeType = resources.getResourceEntryName(binding.recipeTypeRadioGroup.checkedRadioButtonId)

                //Redirect
                val navController = findNavController()
                val action = AddRecipeDetailFragmentDirections.actionAddRecipeDetailFragmentToAddRecipeIngredientsFragment()
                navController.navigate(action)
            } else {
                Toast.makeText(requireContext(), "You must fill in the fields to go next!", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as FellowChefApplication).appComponent.inject(this)
    }

    companion object {
        const val TAG = "Add-Recipe-Detail"
    }
}