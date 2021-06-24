package android.com.fellowchef.ui

import android.com.fellowchef.databinding.RecipeIngredientItemBinding
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

class AddRecipeIngredientListAdapter(val listOfIngredients : MutableList<String>, val context : Context, private val itemClick: (pos: Int) -> Unit) : BaseAdapter(){
    override fun getCount(): Int {
        return listOfIngredients.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding = RecipeIngredientItemBinding.inflate(LayoutInflater.from(context), parent, false)
        binding.ingredientItemText.text = listOfIngredients[position]
        binding.removeIngredientButton.setOnClickListener {
            itemClick.invoke(position)
        }
        return binding.root
    }

}