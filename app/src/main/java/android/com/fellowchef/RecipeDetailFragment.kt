package android.com.fellowchef

import android.com.fellowchef.databinding.FragmentRecipeDetailBinding
import android.com.fellowchef.ui.recipe.Recipe
import android.os.Bundle
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
        viewpager.adjustFragmentsOnPageChange()
        viewpager.adapter = pageAdapter
        val tabLayout = binding.tablayout
        TabLayoutMediator(tabLayout, viewpager) { tab, position ->
            if (position == 0)
                tab.text = getString(R.string.ingredients)
            else if (position == 1)
                tab.text = getString(R.string.instructions)

        }.attach()
    }
    // This function can sit in an Helper file, so it can be shared across your project.
    fun updatePagerHeightForChild(view: View, pager: ViewPager2) {
        view.post {
            val wMeasureSpec =
                    View.MeasureSpec.makeMeasureSpec(view.width, View.MeasureSpec.EXACTLY)
            val hMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            view.measure(wMeasureSpec, hMeasureSpec)

            if (pager.layoutParams.height != view.measuredHeight) {
                pager.layoutParams = (pager.layoutParams)
                        .also { lp ->
                            // applying Fragment Root View Height to
                            // the pager LayoutParams, so they match
                            lp.height = view.measuredHeight
                        }
            }
        }
    }

    private fun ViewPager2.adjustFragmentsOnPageChange(){
        this.registerOnPageChangeCallback((object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)

                // Because the fragment might or might not be created yet,
                // we need to check for the size of the fragmentManager
                // before accessing it.
                if (childFragmentManager.fragments.size > position) {
                    val fragment = childFragmentManager.fragments.get(position)
                    fragment.view?.let {
                        // Now we've got access to the fragment Root View
                        // we will use it to calculate the height and
                        // apply it to the ViewPager2
                        updatePagerHeightForChild(it, binding.viewPager)
                    }
                }

            }
        }))
    }
}

