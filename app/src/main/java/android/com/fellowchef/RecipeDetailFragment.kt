package android.com.fellowchef

import android.com.fellowchef.databinding.FragmentRecipeDetailBinding
import android.com.fellowchef.databinding.ListItemIngredientBinding
import android.com.fellowchef.databinding.ListItemInstructionBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

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
        for (ingredient in recipe.ingredients){
            val bindingRecipeIngredientListItem = ListItemIngredientBinding.inflate(LayoutInflater.from(context))
            bindingRecipeIngredientListItem.ingredientSize.text = "${ingredient.amount} ${ingredient.size}"
            bindingRecipeIngredientListItem.ingredientName.text = ingredient.name
            val ingredientView = bindingRecipeIngredientListItem.root
            binding.ingredientsLayout.addView(ingredientView)
        }

        for (instruction in recipe.instructions){
            val bindingRecipeInstructionListItem = ListItemInstructionBinding.inflate(LayoutInflater.from(context))
            bindingRecipeInstructionListItem.instructionStep.text = "${instruction.step}. ${instruction.text}"
            val instructionView = bindingRecipeInstructionListItem.root
            binding.instructionsLayout.addView(instructionView)
        }
        return binding.root
    }

    companion object {
        const val TAG = "RecipeDetailFragment"
    }
}