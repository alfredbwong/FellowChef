package android.com.fellowchef.ui.dashboard

import android.com.fellowchef.getOrAwaitValue
import android.com.fellowchef.models.Ingredient
import android.com.fellowchef.models.Instruction
import android.com.fellowchef.repo.FakeRecipeRepository
import android.com.fellowchef.repository.models.Resource
import android.com.fellowchef.repository.models.Status
import android.com.fellowchef.ui.recipe.Recipe
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule

@RunWith(MockitoJUnitRunner::class)
class DashboardViewModelTest{

    @Mock
    private lateinit var fakeRecipeRepository: FakeRecipeRepository

    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun test_whenGetLikeRecipes_thenOnSuccessReturnOneLikedRecipe(){
        //Given the ViewModel is ready
        var dashboardViewModel = DashboardViewModel(fakeRecipeRepository)
        Mockito.`when`(fakeRecipeRepository.getListOfLikedRecipes()).thenReturn(
            Single.just(
                mutableListOf(
                    Recipe(
                        1,
                        "recipe1",
                        "recipeImageUrl1",
                        mutableListOf("BREAKFAST"),
                        "shortDesc1",
                        "longDesc1",
                        mutableListOf(Instruction(1, "instruction1")),
                        mutableListOf(Ingredient("name1", "cup", 2F)),
                        2,
                        1,
                        1
                    )
                )
            )
        )
        //When getting recipes
        dashboardViewModel.getLocallyStoredLikedRecipeFeed()
        //Then see that result was success and one recipe came back
        val recipeResult = dashboardViewModel.listOfLikedRecipes.getOrAwaitValue()
        assertThat(recipeResult, CoreMatchers.notNullValue())
        assertThat(recipeResult, CoreMatchers.instanceOf(Resource::class.java))
        assertThat(recipeResult.status, CoreMatchers.`is`(Status.SUCCESS))
        assertThat(recipeResult.data!!.size, CoreMatchers.`is`(1))

    }

    @Test
    fun test_whenGettingLikedRecipes_thenOnErrorReturnErrorMessage(){
        //Given the ViewModel is ready
        var dashboardViewModel = DashboardViewModel(fakeRecipeRepository)
        Mockito.`when`(fakeRecipeRepository.getListOfLikedRecipes()).thenReturn(
            Single.error(Throwable("Error getting recipe filters..."))
        )
        //When getting recipes
        dashboardViewModel.getLocallyStoredLikedRecipeFeed()
        //Then see that result was success and one recipe came back
        val recipeResult = dashboardViewModel.listOfLikedRecipes.getOrAwaitValue()
        assertThat(recipeResult, CoreMatchers.notNullValue())
        assertThat(recipeResult, CoreMatchers.instanceOf(Resource::class.java))
        assertThat(recipeResult.status, CoreMatchers.`is`(Status.ERROR))
        assertThat(recipeResult.message, CoreMatchers.`is`("Error getting recipe filters..."))

    }

    @After
    fun cleanup() {
        Mockito.reset(fakeRecipeRepository)
    }

}