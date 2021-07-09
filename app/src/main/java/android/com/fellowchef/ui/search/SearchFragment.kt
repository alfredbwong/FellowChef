package android.com.fellowchef.ui.search

import android.com.fellowchef.databinding.FragmentSearchBinding
import android.com.fellowchef.ui.component.FilterButton
import android.com.fellowchef.util.CategoryName
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding

    val searchViewModel: SearchViewModel by viewModels()

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

    companion object {
        const val TAG = "SearchFragment"
    }
}