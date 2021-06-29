package android.com.fellowchef

import android.com.fellowchef.databinding.FragmentRecipeDetailIngredientsBinding
import android.com.fellowchef.databinding.FragmentRecipeDetailInstructionsBinding
import android.com.fellowchef.models.IngredientsListAdapter
import android.com.fellowchef.models.Instruction
import android.com.fellowchef.models.InstructionsListAdapter
import android.com.fellowchef.ui.recipe.Recipe
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.compose.navArgument
import androidx.recyclerview.widget.DividerItemDecoration

class RecipeDetailInstructionsFragment : Fragment() {
    private lateinit var binding : FragmentRecipeDetailInstructionsBinding
    private val listInstructionsAdapter = InstructionsListAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentRecipeDetailInstructionsBinding.inflate(inflater, container, false)

        val recipe = arguments?.getParcelable<Recipe>("recipe_instructions")
        var recipeInstructions = recipe?.instructions

        val divider = DividerItemDecoration(requireActivity().applicationContext, DividerItemDecoration.VERTICAL)
        binding.instructionsListView.adapter = listInstructionsAdapter

        listInstructionsAdapter.submitList(recipeInstructions)
        binding.instructionsListView.addItemDecoration(divider);
        return binding.root
    }

    companion object{
        const val TAG = "RecipeDetailInstr"
    }
}