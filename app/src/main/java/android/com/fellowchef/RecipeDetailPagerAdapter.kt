package android.com.fellowchef

import android.com.fellowchef.ui.recipe.Recipe
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.lang.Error

class RecipeDetailPagerAdapter (val fragment: Fragment, val recipe: Recipe) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            val fragment = RecipeDetailIngredientFragment()
            fragment.arguments = Bundle().apply {
                putParcelable("recipe_ingredients", recipe)
            }
            fragment

        } else {
            val fragment = RecipeDetailInstructionsFragment()
            fragment.arguments = Bundle().apply {
                putParcelable("recipe_instructions", recipe)
            }
            fragment
        }
    }


}