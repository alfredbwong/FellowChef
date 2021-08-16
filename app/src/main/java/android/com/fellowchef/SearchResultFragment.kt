package android.com.fellowchef

import android.com.fellowchef.databinding.FragmentSearchResultBinding
import android.com.fellowchef.repository.models.Status
import android.com.fellowchef.ui.recipe.RecipeDashboardAdapter
import android.com.fellowchef.ui.search.SearchResultViewModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchResultFragment : Fragment() {

    private lateinit var binding : FragmentSearchResultBinding
    private val args : SearchResultFragmentArgs by navArgs()

    private val viewModel : SearchResultViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewModel.updateReferenceFilter(args.searchCategoryField)
        viewModel.getRecipeSearchResult()
        binding = FragmentSearchResultBinding.inflate(inflater, container, false)

        val adapter = RecipeDashboardAdapter{
            recipe->
            findNavController().navigate(SearchResultFragmentDirections.actionSearchResultFragmentToRecipeDetailFragment(recipe))
        }
        binding.searchResultRecyclerView.adapter = adapter

        viewModel.listOfRecipeSearchResult.observe(viewLifecycleOwner, Observer {
        resource->
            Log.d(TAG, "resource $resource")
            when (resource.status){
                Status.LOADING->{
                    Log.d(TAG, "Loading...")
                    binding.errorTextResultFragment.visibility = View.GONE
                    binding.progressBarResultFragment.visibility = View.VISIBLE
                    binding.scrollViewDashboard.visibility = View.GONE
                }
                Status.SUCCESS->{
                    Log.d(TAG, "SUCCESS...")
                    adapter.submitList(resource.data)
                    binding.errorTextResultFragment.visibility = View.GONE
                    binding.progressBarResultFragment.visibility = View.GONE
                    binding.scrollViewDashboard.visibility = View.VISIBLE

                }
                Status.ERROR->{
                    Log.d(TAG, "ERROR...")
                    binding.errorTextResultFragment.visibility = View.VISIBLE
                    binding.progressBarResultFragment.visibility = View.GONE
                    binding.scrollViewDashboard.visibility = View.GONE
                }
            }
        })
        return binding.root
    }

    companion object {
        const val TAG = "SearchResultFragment"
    }
}