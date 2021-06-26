package android.com.fellowchef

import android.com.fellowchef.databinding.FragmentRecipeDetailBinding
import android.com.fellowchef.models.IngredientsListAdapter
import android.com.fellowchef.models.InstructionsListAdapter
import android.com.fellowchef.ui.recipe.Recipe
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration


/**
 * A simple [Fragment] subclass.
 * Use the [RecipeDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecipeDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentRecipeDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val recipe = (activity as ViewRecipeActivity).recipe
        binding.recipe = recipe

        val divider = DividerItemDecoration(requireActivity().applicationContext, DividerItemDecoration.VERTICAL)

        val listIngredientAdapter = IngredientsListAdapter()
        binding.ingredientsListView.adapter = listIngredientAdapter
        binding.ingredientsListView.addItemDecoration(divider);
        listIngredientAdapter.submitList(recipe.ingredients)

        val listInstructionAdapter = InstructionsListAdapter()
        binding.instructionsListView.adapter = listInstructionAdapter
        binding.instructionsListView.addItemDecoration(divider);
        listInstructionAdapter.submitList(recipe.instructions)

        return binding.root
    }

    companion object {
        const val TAG = "RecipeDetailFragment"
    }
}