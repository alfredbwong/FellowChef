package android.com.fellowchef.ui.recipe

data class Recipe(
    val id: Int,
    var title: String,
    val image: String,
    var recipeType : String,
    val shortDescription: String
) {

}