package android.com.fellowchef

import android.com.fellowchef.databinding.FragmentAddRecipeInstructionsBinding
import android.com.fellowchef.ui.InstructionListAdapter
import android.com.fellowchef.ui.viewmodel.AddRecipeViewModel
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [AddRecipeInstructionsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddRecipeInstructionsFragment : Fragment() {
    private lateinit var binding: FragmentAddRecipeInstructionsBinding

    @Inject
    lateinit var addRecipeViewModel: AddRecipeViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {


        binding = FragmentAddRecipeInstructionsBinding.inflate(inflater, container, false)

        val adapter = addRecipeViewModel.recipeInstructionsList.value?.let {
            InstructionListAdapter(it, requireContext()) { position ->
                addRecipeViewModel.removeInstruction(position)
            }
        }

        binding.listViewInstructions.adapter = adapter
        binding.buttonAddInstruction.setOnClickListener {
            val input = binding.inputAddInstruction.text.toString()
            if (input.isNotBlank()) {
                addRecipeViewModel.addInstructionToList(input)
            }
        }
        addRecipeViewModel.recipeInstructionsList.observe(viewLifecycleOwner, Observer {

            adapter?.notifyDataSetChanged()

        })
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as AddRecipeActivity).addRecipeComponent.inject(this)
    }

    companion object {
        const val TAG = "Add-Instruction"
    }

}