package android.com.fellowchef

import android.com.fellowchef.databinding.FragmentRecipeDetailBinding
import android.com.fellowchef.databinding.ListItemIngredientBinding
import android.com.fellowchef.ui.recipe.ingredients.RecipeIngredientsAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

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
        val application = requireNotNull(activity).application
        val binding = FragmentRecipeDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val recipe = RecipeDetailFragmentArgs.fromBundle(requireArguments()).selectedRecipe
        binding.recipe = recipe
        Log.i(TAG, "Ingredients : ${recipe.ingredients}")
        for (ingredient in recipe.ingredients){
            val bindingRecipeListItem = ListItemIngredientBinding.inflate(LayoutInflater.from(context))
            bindingRecipeListItem.ingredientSize.text = "${ingredient.amount} ${ingredient.size}"
            bindingRecipeListItem.ingredientName.text = ingredient.name
            val ingredientView = bindingRecipeListItem.root
            binding.ingredientsLayout.addView(ingredientView)
        }
        //Create sample ingredient
        return binding.root
    }

    companion object {
        const val TAG = "RecipeDetailFragment"
    }
}