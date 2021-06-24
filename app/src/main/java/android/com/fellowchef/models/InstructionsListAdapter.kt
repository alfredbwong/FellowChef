package android.com.fellowchef.models

import android.com.fellowchef.databinding.ListItemInstructionBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class InstructionsListAdapter (): ListAdapter<Instruction, InstructionsListAdapter.ViewHolder>(InstructionDiffCallback()) {
    class ViewHolder private constructor(val binding : ListItemInstructionBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind (instruction : Instruction){
            binding.instruction = instruction
            binding.executePendingBindings()
        }
        companion object{
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemInstructionBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}
class InstructionDiffCallback : DiffUtil.ItemCallback<Instruction>(){
    override fun areItemsTheSame(oldItem: Instruction, newItem: Instruction): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Instruction, newItem: Instruction): Boolean {
        return oldItem == newItem
    }

}