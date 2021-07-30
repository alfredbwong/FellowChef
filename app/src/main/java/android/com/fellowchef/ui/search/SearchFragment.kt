package android.com.fellowchef.ui.search

import android.com.fellowchef.R
import android.com.fellowchef.database.model.RecipeCategory
import android.com.fellowchef.databinding.FragmentSearchBinding
import android.com.fellowchef.repository.models.Status
import android.com.fellowchef.ui.component.FilterButton
import android.com.fellowchef.util.CategoryName
import android.os.Bundle
import android.transition.TransitionInflater
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

    private val searchViewModel: SearchViewModel by viewModels()


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        searchViewModel.getRecipeFilters()

        searchViewModel.listOfRecipeFilters.observe(viewLifecycleOwner, Observer { recipeCategoryResource ->
            when(recipeCategoryResource.status){
                Status.SUCCESS->{
                    if (recipeCategoryResource.data != null) {

                        for (category in recipeCategoryResource.data) {
                            when (category.categoryName) {
                                CategoryName.CUISINE.name.toLowerCase() -> {
//                                    addButtonsForCuisineRecipes(category)
                                }
                                CategoryName.DIET_TYPE.name.toLowerCase() -> {
//                                    addButtonsForDietRecipes(category)
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
//                                    addButtonsForOccasionRecipes(category)
                                }
                            }
                        }
                    }

                    binding.nestedScrollView.visibility = View.VISIBLE
                    binding.errorTextSearchFrag.visibility = View.GONE
                    binding.progressBarSearchFrag.visibility = View.GONE
                }
                Status.ERROR->{
                    binding.nestedScrollView.visibility = View.GONE
                    binding.errorTextSearchFrag.visibility = View.VISIBLE
                    binding.progressBarSearchFrag.visibility = View.GONE
                }
                Status.LOADING->{
                    binding.progressBarSearchFrag.visibility = View.VISIBLE
                    binding.nestedScrollView.visibility = View.GONE
                    binding.errorTextSearchFrag.visibility = View.GONE
                }
            }


        })
        binding.invalidateAll()
        return binding.root
    }

    private fun addButtonsForCuisineRecipes(category: RecipeCategory) {
        for (categoryField in category.categoryFields) {
            binding.cuisineFieldsLayout.addView(FilterButton(requireContext(), binding.cuisineFieldsLayout, categoryField))
        }
    }

    private fun addButtonsForDietRecipes(category: RecipeCategory) {
        for (categoryField in category.categoryFields) {
            binding.dietFieldsLayout.addView(FilterButton(requireContext(), binding.dietFieldsLayout, categoryField))
        }
    }

    private fun addButtonsForOccasionRecipes(category: RecipeCategory) {
        for (categoryField in category.categoryFields) {
            binding.occasionFieldsLayout.addView(FilterButton(requireContext(), binding.occasionFieldsLayout, categoryField))
        }
    }

    companion object {
        const val TAG = "SearchFragment"
    }
}