package android.com.fellowchef.ui.component

import android.com.fellowchef.R
import android.com.fellowchef.databinding.HomeSectionBinding
import android.com.fellowchef.ui.recipe.Recipe
import android.com.fellowchef.ui.recipe.RecipeAdapter
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

class SectionLayout(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs ) {
    private var binding : HomeSectionBinding = HomeSectionBinding.inflate(LayoutInflater.from(context), this,  true)
    private lateinit var adapter : RecipeAdapter
    init{
        val customAttributeStyle = context.obtainStyledAttributes(attrs, R.styleable.SectionLayout, 0 ,0)

        adapter = RecipeAdapter{
            Toast.makeText(context, "You have clicked me", Toast.LENGTH_SHORT).show()
        }

        //Set the recyclerView to be horizontal, this cannot be done in XML and must be done in code
        binding.sectionRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.sectionRecyclerView.adapter = adapter
        try{
            binding.sectionTitle.text = customAttributeStyle.getString(R.styleable.SectionLayout_sectionTitle)

        } finally {
            customAttributeStyle.recycle()
        }
    }

    fun refreshList(recipeList: List<Recipe>) {
        adapter.submitList(recipeList)
    }




}