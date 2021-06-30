package android.com.fellowchef.util

import android.com.fellowchef.ui.recipe.Recipe

fun filterRecipesByTag(listOfRecipes: List<Recipe>, listOfTags : List<RecipeType>): MutableList<Recipe> {
    val returnListOfRecipes = mutableListOf<Recipe>()
    for (recipe in listOfRecipes){
        for (tag in listOfTags){
//            if (recipe.recipeType.contains(tag.name)){
//                returnListOfRecipes.add(recipe)
//                break;
//            }
        }
    }
    return returnListOfRecipes

}