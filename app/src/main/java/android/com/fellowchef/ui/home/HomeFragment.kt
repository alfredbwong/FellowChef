package android.com.fellowchef.ui.home

import android.com.fellowchef.MainActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import android.com.fellowchef.databinding.FragmentHomeBinding
import android.com.fellowchef.repository.models.Status
import android.content.Context
import android.opengl.Visibility
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    //Hilt says you should retrieve the ViewModel from ViewModelProviderAPI, otherwise would result in multiple instances https://dagger.dev/hilt/view-model.html
    val homeViewModel : HomeViewModel by viewModels()

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
            when (recipeList.status) {
                Status.SUCCESS->{

                    recipeList.data?.let { binding.trendingSection.refreshList(it) }
                    showSuccessComponents()
                }
                Status.ERROR->{

                }
                Status.LOADING->{
                    showLoadingComponents()
                }
            }
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

    private fun showSuccessComponents() {
        binding.progressBar.visibility = View.INVISIBLE
        binding.homeScrollView.visibility = View.VISIBLE
    }

    private fun showLoadingComponents() {
        binding.progressBar.visibility = View.VISIBLE
        binding.homeScrollView.visibility = View.INVISIBLE
    }


    companion object {
        const val TAG = "HomeFragment"
    }
}