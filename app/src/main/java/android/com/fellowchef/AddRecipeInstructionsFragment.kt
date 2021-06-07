package android.com.fellowchef

import android.com.fellowchef.ui.viewmodel.AddRecipeViewModel
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [AddRecipeInstructionsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddRecipeInstructionsFragment : Fragment() {

    @Inject
    lateinit var addRecipeViewModel: AddRecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_recipe_instructions, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as AddRecipeActivity).addRecipeComponent.inject(this)
    }

    companion object {
        const val TAG = "Add-Instruction"
    }

}