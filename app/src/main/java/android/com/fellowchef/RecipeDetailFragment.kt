package android.com.fellowchef

import android.com.fellowchef.databinding.FragmentRecipeDetailBinding
import android.com.fellowchef.models.IngredientsListAdapter
import android.com.fellowchef.models.InstructionsListAdapter
import android.com.fellowchef.ui.recipe.Recipe
import android.com.fellowchef.ui.viewmodel.RecipeDetailViewModel
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * Use the [RecipeDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class RecipeDetailFragment : Fragment() {
    private lateinit var pageAdapter: RecipeDetailPagerAdapter
    private lateinit var binding: FragmentRecipeDetailBinding
    private lateinit var recipe: Recipe
    private var isRecipeLiked: Boolean  = false


    private val recipeDetailViewModel : RecipeDetailViewModel by viewModels()

    private val args : RecipeDetailFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recipeDetailViewModel.isRecipeLiked.observe(this, Observer { isLiked ->
            isRecipeLiked = isLiked
            requireActivity().invalidateOptionsMenu()

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeDetailBinding.inflate(inflater)
        recipe = args.recipe
        recipeDetailViewModel.updateReferencedRecipe(recipe)
        binding.lifecycleOwner = this


        val ingredientAdapter = IngredientsListAdapter()
        binding.ingredientsListView.adapter = ingredientAdapter.apply{
            submitList(recipe.ingredients)
        }

        binding.ingredientsListView.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        val instructionsAdapter = InstructionsListAdapter()
        binding.instructionsListView.adapter = instructionsAdapter.apply{
            submitList(recipe.instructions)
        }
        binding.recipe = recipe
        setHasOptionsMenu(true)
        return binding.root
    }

    companion object {
        const val TAG = "RecipeDetailFragment"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pageAdapter = RecipeDetailPagerAdapter(this, recipe)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.recipe_detail_menu_liked, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.likeButton -> {
                recipeDetailViewModel.isRecipeLiked.value = !isRecipeLiked
                if (isRecipeLiked) {
                    recipeDetailViewModel.addLikedRecipe(recipe)
                } else {
                    recipeDetailViewModel.removeRecipeFromLiked(recipe.id)

                }
                requireActivity().invalidateOptionsMenu()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val likeButton = menu.findItem(R.id.likeButton)

        if (isRecipeLiked){
            likeButton.setIcon(R.drawable.ic_baseline_favorite_24)
        } else {
            likeButton.setIcon(R.drawable.ic_baseline_favorite_border_24)
        }
    }
}

