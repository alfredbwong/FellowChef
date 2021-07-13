package android.com.fellowchef

import android.com.fellowchef.databinding.FragmentSearchResultBinding
import android.com.fellowchef.repository.models.Status
import android.com.fellowchef.ui.recipe.RecipeDashboardAdapter
import android.com.fellowchef.ui.search.SearchResultViewModel
import android.os.Bundle
import android.transition.TransitionInflater
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade)
        enterTransition= inflater.inflateTransition(R.transition.fade)

    }

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
                    binding.errorTextResultFragment.visibility = View.GONE
                    binding.progressBarResultFragment.visibility = View.VISIBLE
                    binding.scrollViewDashboard.visibility = View.GONE
                }
                Status.SUCCESS->{
                    adapter.submitList(resource.data)
                    binding.errorTextResultFragment.visibility = View.GONE
                    binding.progressBarResultFragment.visibility = View.GONE
                    binding.scrollViewDashboard.visibility = View.VISIBLE

                }
                Status.ERROR->{
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