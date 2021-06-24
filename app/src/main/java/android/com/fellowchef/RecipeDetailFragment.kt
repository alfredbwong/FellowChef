package android.com.fellowchef

import android.com.fellowchef.databinding.FragmentRecipeDetailBinding
import android.com.fellowchef.databinding.ListItemIngredientBinding
import android.com.fellowchef.databinding.ListItemInstructionBinding
import android.com.fellowchef.models.IngredientsListAdapter
import android.com.fellowchef.models.InstructionsListAdapter
import android.com.fellowchef.ui.home.HomeViewModel
import android.com.fellowchef.util.Utility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

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
        val recipe = RecipeDetailFragmentArgs.fromBundle(requireArguments()).selectedRecipe
        binding.recipe = recipe

        val listIngredientAdapter = IngredientsListAdapter()
        binding.ingredientsListView.adapter = listIngredientAdapter
        listIngredientAdapter.submitList(recipe.ingredients)

        val listInstructionAdapter = InstructionsListAdapter()
        binding.instructionsListView.adapter = listInstructionAdapter
        listInstructionAdapter.submitList(recipe.instructions)

        return binding.root
    }

    companion object {
        const val TAG = "RecipeDetailFragment"
    }
}