package android.com.fellowchef.ui.component

import android.com.fellowchef.databinding.ButtonFilterBinding
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class FilterButton(context: Context, parent : ViewGroup, text: String) : View(context) {
    init {
        val binding = ButtonFilterBinding.inflate(LayoutInflater.from(context), parent, true)
        binding.filterButton.text = text
    }
}

