package android.com.fellowchef.ui.search

import android.com.fellowchef.MainActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.com.fellowchef.R
import android.com.fellowchef.databinding.FragmentSearchBinding
import android.com.fellowchef.di.MainRecipeActivityScope
import android.com.fellowchef.di.SearchRecipeActivityScope
import android.com.fellowchef.ui.component.FilterButton
import android.com.fellowchef.ui.home.HomeViewModel
import android.com.fellowchef.util.CategoryName
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import javax.inject.Inject

@MainRecipeActivityScope
class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding

    @Inject
    lateinit var searchViewModel: SearchViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        searchViewModel.listOfRecipeFilters.observe(viewLifecycleOwner, Observer { recipeCateogryResource ->
            if (recipeCateogryResource.data != null) {

                for (category in recipeCateogryResource.data) {
                    when (category.categoryName) {
                        CategoryName.CUISINE.name.toLowerCase() -> {
                            for (categoryField in category.categoryFields) {
                                binding.cuisineFieldsLayout.addView(FilterButton(requireContext(), binding.cuisineFieldsLayout, categoryField))

                            }
                        }
                        CategoryName.DIET_TYPE.name.toLowerCase() -> {
                            for (categoryField in category.categoryFields) {
                                binding.dietFieldsLayout.addView(FilterButton(requireContext(), binding.dietFieldsLayout, categoryField))

                            }
                        }
                        CategoryName.MEAL_TYPE.name.toLowerCase() -> {
                            for (categoryField in category.categoryFields) {
                                binding.mealTypeFieldsLayout.addView(FilterButton(requireContext(), binding.mealTypeFieldsLayout, categoryField))

                            }
                        }
                        CategoryName.DIFFICULTY.name.toLowerCase() -> {
                            for (categoryField in category.categoryFields) {
                                binding.difficultyFieldsLayout.addView(FilterButton(requireContext(), binding.difficultyFieldsLayout, categoryField))

                            }
                        }
                        CategoryName.OCCASION_TYPE.name.toLowerCase() -> {
                            for (categoryField in category.categoryFields) {
                                binding.occasionFieldsLayout.addView(FilterButton(requireContext(), binding.occasionFieldsLayout, categoryField))

                            }
                        }
                    }
                }
            }

        })
        binding.invalidateAll()
        return binding.root
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).mainRecipeComponent.inject(this)
    }

    companion object {
        const val TAG = "SearchFragment"
    }
}