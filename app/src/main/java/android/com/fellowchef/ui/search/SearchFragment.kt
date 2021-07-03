package android.com.fellowchef.ui.search

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
import android.com.fellowchef.databinding.FragmentSearchBinding
import android.com.fellowchef.di.MainRecipeActivityScope
import android.com.fellowchef.di.SearchRecipeActivityScope
import android.com.fellowchef.ui.home.HomeViewModel
import android.content.Context
import android.util.Log
import javax.inject.Inject

@MainRecipeActivityScope
class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding

    @Inject
    lateinit var searchViewModel : SearchViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        searchViewModel.listOfRecipeFilters.observe(viewLifecycleOwner, Observer {
            recipeCateogryResource ->
            Log.i(TAG, "listOfRecipeFilters = ${recipeCateogryResource}")
            binding.responseText.text = recipeCateogryResource.data.toString()
        })
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).mainRecipeComponent.inject(this)
    }
    companion object{
        const val TAG = "SearchFragment"
    }
}