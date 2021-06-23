package android.com.fellowchef

import android.com.fellowchef.databinding.FragmentRecipeDetailBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RecipeDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecipeDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val application = requireNotNull(activity).application
        val binding = FragmentRecipeDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val recipe = RecipeDetailFragmentArgs.fromBundle(requireArguments()).selectedRecipe
        binding.recipe = recipe

        return binding.root
    }

    companion object {
        const val TAG = "RecipeDetailFragment"
    }
}