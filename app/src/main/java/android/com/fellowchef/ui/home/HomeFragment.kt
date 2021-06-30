package android.com.fellowchef.ui.home

import android.com.fellowchef.AddRecipeActivity
import android.com.fellowchef.FellowChefApplication
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
import android.com.fellowchef.databinding.FragmentHomeBinding
import android.com.fellowchef.di.HomeRecipeComponent
import android.com.fellowchef.ui.recipe.Recipe
import android.com.fellowchef.ui.recipe.RecipeAdapter
import android.com.fellowchef.ui.viewmodel.AddRecipeViewModel
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import javax.inject.Inject

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var homeViewModel : HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textHome.text = it
        })
        homeViewModel.listOfRecipes.observe(viewLifecycleOwner, Observer { recipeList ->
            recipeList.data?.let { binding.trendingSection.refreshList(it) }
        })
        homeViewModel.listOfRecipesTrending.observe(viewLifecycleOwner, Observer { recipeList ->
            binding.popularThisWeekSection.refreshList(recipeList)
        })
        homeViewModel.isShowToast.observe(viewLifecycleOwner, Observer { isShowToast ->
            if (isShowToast) {
                homeViewModel.clearToast()
            }
        })
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //Use Dagger to inject this fragment
        (activity as MainActivity).homeRecipeComponent.inject(this)
    }

    companion object {
        const val TAG = "HomeFragment"
    }
}