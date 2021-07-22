package android.com.fellowchef.ui.home

import android.com.fellowchef.databinding.FragmentHomeBinding
import android.com.fellowchef.repository.models.Status
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    //Hilt says you should retrieve the ViewModel from ViewModelProviderAPI, otherwise would result in multiple instances https://dagger.dev/hilt/view-model.html
    private val homeViewModel : HomeViewModel by viewModels()


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
                    recipeList.data?.filter {
                        recipe ->
                        recipe.recipeType.contains("TRENDING")
                    }.let {
                        if (it != null) {
                            binding.trendingSection.refreshList(it)
                        }

                    }
                    recipeList.data?.filter {
                            recipe ->
                        recipe.recipeType.contains("POPULAR")
                    }.let {
                        if (it != null) {
                            binding.popularThisWeekSection.refreshList(it)
                        }

                    }
                    showSuccessComponents()
                }
                Status.ERROR->{
                    showErrorComponents(recipeList.message)
                }
                Status.LOADING->{
                    showLoadingComponents()
                }
            }
        })
        homeViewModel.isShowToast.observe(viewLifecycleOwner, Observer { isShowToast ->
            if (isShowToast) {
                homeViewModel.clearToast()
            }
        })
        return binding.root
    }

    private fun showErrorComponents(message: String?) {
        binding.errorText.text = message
        binding.errorText.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
        binding.homeScrollView.visibility = View.GONE
    }

    private fun showSuccessComponents() {
        binding.progressBar.visibility = View.GONE
        binding.errorText.visibility = View.GONE
        binding.homeScrollView.visibility = View.VISIBLE
    }

    private fun showLoadingComponents() {
        binding.progressBar.visibility = View.VISIBLE
        binding.homeScrollView.visibility = View.GONE
        binding.errorText.visibility = View.GONE
    }


    companion object {
        const val TAG = "HomeFragment"
    }
}