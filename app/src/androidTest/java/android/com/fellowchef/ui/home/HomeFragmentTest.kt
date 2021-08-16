package android.com.fellowchef.ui.home

import android.com.fellowchef.MainActivity
import android.com.fellowchef.repo.FakeRecipeAndroidTestRepository
import android.com.fellowchef.repository.RecipeRepository
import android.com.fellowchef.util.launchFragmentInHiltContainer
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class HomeFragmentTest{
    @get:Rule
    var hiltRule = HiltAndroidRule(this)
    // single task rule
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Inject
    lateinit var recipeRepository: RecipeRepository



    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        hiltRule.inject()
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

    }
    @Test
    fun test_whenGetRecipes_thenTestFailureReturnError() {
        //Given ViewModel is ready
        var homeViewModel = HomeViewModel(recipeRepository)
        launchFragmentInHiltContainer<HomeFragment> (){  }


    }
}
