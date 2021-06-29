package android.com.fellowchef

import android.com.fellowchef.databinding.FragmentRecipeDetailBinding
import android.com.fellowchef.ui.recipe.Recipe
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator


/**
 * A simple [Fragment] subclass.
 * Use the [RecipeDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecipeDetailFragment : Fragment() {
    private lateinit var pageAdapter: RecipeDetailPagerAdapter
    private lateinit var viewpager: ViewPager2
    private lateinit var binding: FragmentRecipeDetailBinding
    private lateinit var recipe: Recipe

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentRecipeDetailBinding.inflate(inflater)
        recipe = (activity as ViewRecipeActivity).recipe

        binding.lifecycleOwner = this
        binding.recipe = recipe
        return binding.root
    }

    companion object {
        const val TAG = "RecipeDetailFragment"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pageAdapter = RecipeDetailPagerAdapter(this, recipe)
        viewpager = binding.viewPager
        viewpager.adapter = pageAdapter
        val tabLayout = binding.tablayout
        TabLayoutMediator(tabLayout, viewpager) { tab, position ->
            if (position == 0)
                tab.text = getString(R.string.ingredients)
            else if (position == 1)
                tab.text = getString(R.string.instructions)

        }.attach()
    }
}