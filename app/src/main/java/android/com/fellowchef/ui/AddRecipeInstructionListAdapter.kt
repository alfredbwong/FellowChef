package android.com.fellowchef.ui

import android.com.fellowchef.databinding.RecipeInstructionItemBinding
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

class AddRecipeInstructionListAdapter (private val listOfInstructions : MutableList<String>, private val context : Context, private val itemClick : (pos : Int) -> Unit) : BaseAdapter(){
    override fun getCount(): Int {
        return listOfInstructions.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding = RecipeInstructionItemBinding.inflate(LayoutInflater.from(context), parent, false)
        binding.textInstruction.text = "${position+1}. ${listOfInstructions[position]}"

        binding.removeInstructionButton.setOnClickListener{
            itemClick.invoke(position)
        }
        return binding.root
    }
}