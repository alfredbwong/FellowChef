package android.com.fellowchef.ui.recipe

import android.com.fellowchef.databinding.DashboardRecipeListItemBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class RecipeDashboardAdapter ( private val listener : (Recipe) -> Unit) : ListAdapter<Recipe, RecipeDashboardAdapter.ViewHolder>(RecipeDashboardDiffCallback()) {

    class ViewHolder private constructor (private var binding : DashboardRecipeListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        /**
        This will bind the recipe's field to the view holder item
         **/
        fun bind (item: Recipe){
            binding.recipe = item
            binding.executePendingBindings()
        }
        companion object{
            /**
             *  Inflate the view for a view using recipe_display_card_item (RecipeDisplayCardItemBinding) and then bind it
             **/
            fun from(parent: ViewGroup) : ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DashboardRecipeListItemBinding.inflate(layoutInflater, parent,false)

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
        holder.itemView.setOnClickListener{listener(item)}
    }


}


/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minimum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class RecipeDashboardDiffCallback : DiffUtil.ItemCallback<Recipe>() {
    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem == newItem
    }
}