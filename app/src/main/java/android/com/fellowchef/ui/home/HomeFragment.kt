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
        //Test response string
        homeViewModel.response.observe(viewLifecycleOwner, Observer {
            responseString ->
            binding.responseText.text = responseString
        })

        homeViewModel.listOfRecipesBreakfast.observe(viewLifecycleOwner, Observer {
            recipeList ->
                binding.testSection1.refreshList(recipeList)
        })
        return binding.root
    }


}