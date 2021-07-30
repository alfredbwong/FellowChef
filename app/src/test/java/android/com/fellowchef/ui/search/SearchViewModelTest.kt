package android.com.fellowchef.ui.search

import android.com.fellowchef.database.model.RecipeCategory
import android.com.fellowchef.getOrAwaitValue
import android.com.fellowchef.repo.FakeRecipeRepository
import android.com.fellowchef.repository.models.Resource
import android.com.fellowchef.repository.models.Status
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Assert.*
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
class SearchViewModelTest{

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
    fun test_whenGetRecipeFilters_thenOnSuccessReturnTwoFilters(){
        //Given the ViewModel is ready
        var searchViewModel = SearchViewModel(fakeRecipeRepository)
        Mockito.`when`(fakeRecipeRepository.getRecipeFiltersFeed()).thenReturn(
            Single.just(
                mutableListOf(
                    RecipeCategory(
                        "CATEGORY_NAME_A",
                        mutableListOf("CATEGORY_A_1", "CATEGORY_A_2")
                    ),
                    RecipeCategory(
                        "CATEGORY_NAME_B",
                        mutableListOf("CATEGORY_B_1", "CATEGORY_B_2", "CATEGORY_B_3")
                    ),
                )
            )
        )
        //When getting recipes
        searchViewModel.getRecipeFilters()
        //Then see that result was success and one recipe came back
        val recipeResult = searchViewModel.listOfRecipeFilters.getOrAwaitValue()
        assertThat(recipeResult, CoreMatchers.notNullValue())
        assertThat(recipeResult, CoreMatchers.instanceOf(Resource::class.java))
        assertThat(recipeResult.status, CoreMatchers.`is`(Status.SUCCESS))
        assertThat(recipeResult.data!!.size, CoreMatchers.`is`(2))

    }

    @Test
    fun test_whenGetRecipeFilters_thenOnErrorReturnResourceError(){
        //Given the ViewModel is ready
        var searchViewModel = SearchViewModel(fakeRecipeRepository)
        Mockito.`when`(fakeRecipeRepository.getRecipeFiltersFeed()).thenReturn(
            Single.error(Throwable("Error getting recipe filters..."))
        )
        //When getting recipes
        searchViewModel.getRecipeFilters()
        //Then see that result was success and one recipe came back
        val recipeResult = searchViewModel.listOfRecipeFilters.getOrAwaitValue()
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
