package android.com.fellowchef.ui.dashboard

import android.com.fellowchef.R
import android.com.fellowchef.databinding.FragmentDashboardBinding
import android.com.fellowchef.repository.models.Status
import android.com.fellowchef.ui.recipe.RecipeDashboardAdapter
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {


    private val dashboardViewModel: DashboardViewModel by viewModels()
    private lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val adapter = RecipeDashboardAdapter(){
            recipe->
            findNavController().navigate(DashboardFragmentDirections.actionNavigationDashboardToRecipeDetailFragment(recipe))

        }
        binding.myRecipeRecyclerView.adapter = adapter
        dashboardViewModel.listOfLikedRecipes.observe(viewLifecycleOwner, Observer { listOfRecipes ->
            when (listOfRecipes.status) {
                (Status.SUCCESS) -> {
                    adapter.submitList(listOfRecipes.data)

                    binding.errorTextDashboardFrag.visibility = View.GONE
                    binding.progressBarDashboardFrag.visibility = View.VISIBLE
                    binding.scrollViewDashboard.visibility = View.GONE
                }
                (Status.ERROR) -> {

                    binding.errorTextDashboardFrag.visibility = View.VISIBLE
                    binding.progressBarDashboardFrag.visibility = View.GONE
                    binding.scrollViewDashboard.visibility = View.GONE
                }
                (Status.LOADING) -> {

                    binding.errorTextDashboardFrag.visibility = View.GONE
                    binding.progressBarDashboardFrag.visibility = View.VISIBLE
                    binding.scrollViewDashboard.visibility = View.GONE
                }
            }
        })

        return binding.root
    }

    companion object {
        const val TAG = "DashboardFragment"
    }
}