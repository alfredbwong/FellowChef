package android.com.fellowchef

import android.com.fellowchef.databinding.ActivityViewRecipeBinding
import android.com.fellowchef.ui.recipe.Recipe
import android.com.fellowchef.ui.viewmodel.RecipeDetailViewModel
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class ViewRecipeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityViewRecipeBinding
    lateinit var recipe : Recipe
    private var isRecipeLiked: Boolean  = false

    private val viewModel : RecipeDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_recipe)



        //Get the intent and extract the recipe
        recipe = intent.getParcelableExtra<Recipe>("selected_recipe") as Recipe
        viewModel.updateReferencedRecipe(recipe)
        viewModel.isRecipeLiked.observe(this, Observer {
                isLiked ->
            isRecipeLiked = isLiked
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (isRecipeLiked)
            menuInflater.inflate(R.menu.recipe_detail_menu_liked, menu)
        else
            menuInflater.inflate(R.menu.recipe_detail_menu_unliked, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if( id == R.id.likeButton){
            isRecipeLiked = !isRecipeLiked
            viewModel.addOrRemoveLikedRecipe(recipe.id, isRecipeLiked)
            invalidateOptionsMenu()
            return true
        }
        return super.onOptionsItemSelected(item)

    }
    companion object{
        const val TAG = "ViewRecipeActivity"
    }

}