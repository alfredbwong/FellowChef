package android.com.fellowchef.ui.home

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
import android.com.fellowchef.ui.recipe.Recipe
import android.com.fellowchef.ui.recipe.RecipeAdapter
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textHome.text = it
        })

        homeViewModel.listOfRecipesBreakfast.observe(viewLifecycleOwner, Observer {
            recipeList ->
            Log.i(TAG, "recipeList: $recipeList")
                binding.trendingSection.refreshList(recipeList)
        })
        homeViewModel.isShowToast.observe(viewLifecycleOwner, Observer {
            isShowToast ->
            if (isShowToast){
//                Toast.makeText(requireContext(), homeViewModel.toastErrorMessage.value, Toast.LENGTH_SHORT).show()
                homeViewModel.clearToast()
            }
        })
        return binding.root
    }

    companion object{
        const val TAG = "HomeFragment"
    }
}