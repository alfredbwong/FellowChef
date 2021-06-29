package android.com.fellowchef

import android.com.fellowchef.databinding.FragmentRecipeDetailIngredientsBinding
import android.com.fellowchef.models.IngredientsListAdapter
import android.com.fellowchef.ui.recipe.Recipe
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration

class RecipeDetailIngredientFragment : Fragment() {
    private lateinit var binding: FragmentRecipeDetailIngredientsBinding
    private val listIngredientAdapter = IngredientsListAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRecipeDetailIngredientsBinding.inflate(inflater, container, false)
        val recipe = arguments?.getParcelable<Recipe>("recipe_ingredients")
        var recipeIngredients = recipe?.ingredients
        val divider = DividerItemDecoration(requireActivity().applicationContext, DividerItemDecoration.VERTICAL)

        binding.ingredientsListView.adapter = listIngredientAdapter
        listIngredientAdapter.submitList(recipeIngredients)

        binding.ingredientsListView.addItemDecoration(divider);
        return binding.root
    }

    companion object {
        const val TAG = "RecipeDetailIngredientFragment"
    }


}