package com.example.tbc_android_2025.item_wrapper.item

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.tbc_android_2025.databinding.ItemEditTextBinding

class ItemAdapter : ListAdapter<Item, ItemAdapter.ItemViewHolder>(ItemDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemViewHolder(ItemEditTextBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ItemViewHolder(private val binding: ItemEditTextBinding) : ViewHolder(binding.root) {

        fun bind(item: Item) {
            val et = binding.editText
            et.hint = if (item.required) "${item.hint} *" else item.hint
            et.isEnabled = item.is_active

            et.inputType = when(item.keyboard) {
                KeyboardType.TEXT -> android.text.InputType.TYPE_CLASS_TEXT
                KeyboardType.NUMBER -> android.text.InputType.TYPE_CLASS_NUMBER
                KeyboardType.EMAIL -> android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                null -> android.text.InputType.TYPE_CLASS_TEXT
            }

            Glide.with(et.context)
                .asDrawable()
                .load(item.icon)
                .into(object : CustomTarget<Drawable>() {
                    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                        et.setCompoundDrawablesWithIntrinsicBounds(null, null, resource, null)
                    }
                    override fun onLoadCleared(placeholder: Drawable?) {
                        et.setCompoundDrawablesWithIntrinsicBounds(null, null, placeholder, null)
                    }
                }
            )
        }
    }
}
