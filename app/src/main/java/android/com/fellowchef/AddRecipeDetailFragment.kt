package android.com.fellowchef

import android.com.fellowchef.databinding.FragmentAddRecipeDetailBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * A simple [Fragment] subclass.
 * Use the [AddRecipeDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddRecipeDetailFragment : Fragment() {
    private lateinit var binding: FragmentAddRecipeDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentAddRecipeDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    companion object {
        const val TAG = "Add-Recipe-Detail"
    }
}