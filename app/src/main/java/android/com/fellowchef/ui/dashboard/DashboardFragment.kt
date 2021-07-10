package android.com.fellowchef.ui.dashboard

import android.com.fellowchef.ViewRecipeActivity
import android.com.fellowchef.databinding.FragmentDashboardBinding
import android.com.fellowchef.repository.models.Status
import android.com.fellowchef.ui.recipe.RecipeAdapter
import android.com.fellowchef.ui.recipe.RecipeDashboardAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val adapter = RecipeDashboardAdapter(){

                recipe ->
            val intent = Intent(context, ViewRecipeActivity::class.java).apply{
                putExtra("selected_recipe", recipe)
            }
            requireActivity().startActivity(intent)
        }
        binding.myRecipeRecyclerView.adapter = adapter
        dashboardViewModel.likedRecipes.observe(viewLifecycleOwner, Observer {
            listOfRecipes->
            when(listOfRecipes.status){
                (Status.SUCCESS)->{
                    adapter.submitList(listOfRecipes.data)

                }
                (Status.ERROR)->{

                }
                (Status.LOADING)->{

                }
            }
        })

        return binding.root
    }
companion object{
    const val TAG = "DashboardFragment"
}
}