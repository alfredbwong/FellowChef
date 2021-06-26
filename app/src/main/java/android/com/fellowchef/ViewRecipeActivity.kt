package android.com.fellowchef

import android.com.fellowchef.ui.recipe.Recipe
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ViewRecipeActivity : AppCompatActivity() {

    lateinit var recipe : Recipe
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_view_recipe)
        //Get the intent and extract the recipe
        recipe = intent.getParcelableExtra<Recipe>("selected_recipe") as Recipe

    }
}