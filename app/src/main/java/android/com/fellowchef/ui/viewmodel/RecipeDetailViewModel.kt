package android.com.fellowchef.ui.viewmodel

import android.com.fellowchef.repository.RecipeRepository
import android.com.fellowchef.ui.recipe.Recipe
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.stream.Collectors
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.N)
@HiltViewModel
class RecipeDetailViewModel @Inject constructor(var recipeRepository: RecipeRepository ): ViewModel() {

    var recipeReferenced = MediatorLiveData<Recipe>()
    val isRecipeLiked = MediatorLiveData<Boolean>()

    private val compositeDisposable by lazy {CompositeDisposable()}

    init {
        checkIfRecipeIsLiked()
    }
    @RequiresApi(Build.VERSION_CODES.N)
    private fun checkIfRecipeIsLiked() {
        compositeDisposable.add(recipeRepository.getListOfLikedRecipes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listOfRecipes ->
                val ids = listOfRecipes.stream().map { recipe -> recipe.id }.collect(Collectors.toList())
                if (ids.contains(recipeReferenced.value?.id)){
                    isRecipeLiked.value = true
                }
            },{
                isRecipeLiked.value = false
            }))

    }

    fun updateReferencedRecipe(recipe: Recipe) {
        recipeReferenced.value = recipe
    }

    fun removeRecipeFromLiked(id: Int) {
        compositeDisposable.add(recipeRepository.removeRecipeFromLiked(id)
                .subscribeOn(Schedulers.io())
            .subscribe({
                Log.d(TAG, "Success deleting from DB... $it")
            },{
                Log.d(TAG, "Error deleting from DB... $it")
            }))


    }

    fun addLikedRecipe(recipe: Recipe) {
        compositeDisposable.add(recipeRepository.addRecipeToLiked(recipe)
                .subscribeOn(Schedulers.io())
            .subscribe({
                Log.d(TAG, "Success inserting recipe into DB ... $it")
            },{
                Log.d(TAG, "Error inserting recipe into DB ... ${it.message}")

            }))

    }

    companion object{
        const val TAG = "RecipeDetailViewModel"
    }

}