package android.com.fellowchef

import android.com.fellowchef.databinding.FragmentSearchResultBinding
import android.com.fellowchef.repository.models.Status
import android.com.fellowchef.ui.recipe.RecipeDashboardAdapter
import android.com.fellowchef.ui.search.SearchResultViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        viewModel.getRecipeSearchResult(args.searchCategoryField)
        binding = FragmentSearchResultBinding.inflate(inflater, container, false)

        val adapter = RecipeDashboardAdapter{
            recipe->
            findNavController().navigate(SearchResultFragmentDirections.actionSearchResultFragmentToRecipeDetailFragment(recipe))
        }
        binding.searchResultRecyclerView.adapter = adapter

        viewModel.listOfRecipeSearchResult.observe(viewLifecycleOwner, Observer {
        resource->
            when (resource.status){
                Status.LOADING->{

                }
                Status.SUCCESS->{
                    adapter.submitList(resource.data)

                }
                Status.ERROR->{

                }
            }
        })
        return binding.root
    }

    companion object {
        const val TAG = "SearchResultFragment"
    }
}