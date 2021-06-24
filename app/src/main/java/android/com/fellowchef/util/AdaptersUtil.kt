package android.com.fellowchef.util

import android.com.fellowchef.models.Ingredient
import android.com.fellowchef.models.Instruction
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
                .load(imgUri)
                .into(imgView)

    }
}
@BindingAdapter("ingredientAmountAndSize")
fun bindIngredientAmountAndSize(textView: TextView, ingredient : Ingredient){
    textView.text = "${ingredient.amount} ${ingredient.size}"
}

@BindingAdapter("instructionStepAndText")
fun bindInstructionStepAndText(textView: TextView, instruction : Instruction){
    textView.text = "${instruction.step}. ${instruction.text}"
}
