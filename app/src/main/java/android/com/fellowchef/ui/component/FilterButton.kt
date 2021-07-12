package android.com.fellowchef.ui.component

import android.com.fellowchef.databinding.ButtonFilterBinding
import android.com.fellowchef.ui.search.SearchFragmentDirections
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.findNavController

class FilterButton(context: Context, parent: ViewGroup,text: String): LinearLayout(context) {

    init {
        val binding = ButtonFilterBinding.inflate(LayoutInflater.from(context), parent, true)
        binding.filterButton.text = text
        binding.filterButton.setOnClickListener{
            findNavController().navigate(SearchFragmentDirections.actionNavigationSearchToSearchResultFragment(text))
        }
    }



}

