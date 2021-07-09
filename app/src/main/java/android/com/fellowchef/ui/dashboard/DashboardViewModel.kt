package android.com.fellowchef.ui.dashboard

import android.com.fellowchef.database.RecipeDatabase
import android.com.fellowchef.repository.RecipeRepository
import android.com.fellowchef.service.BASE_URL
import android.com.fellowchef.service.FellowChefRecipeService
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class DashboardViewModel(applicationContext: Context) : ViewModel() {
    private val repository: RecipeRepository =
            RecipeRepository(
                    Retrofit.Builder().baseUrl(BASE_URL)
                            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().add(KotlinJsonAdapterFactory()).build()))
                            .build()
                            .create(FellowChefRecipeService::class.java),
                    RecipeDatabase.getInstance(applicationContext).recipeDao()
            )
    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text
    init{
        getLocallyStoredRecipeFeed()
    }

    private fun getLocallyStoredRecipeFeed() {
        repository.getLocallyStoredRecipeFeed()
    }
}