package com.example.tbc_android_2025.presentation.screens.home.post

import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import com.example.tbc_android_2025.commons.Strings
import com.example.tbc_android_2025.presentation.commons.BaseAdapter
import com.example.tbc_android_2025.presentation.extensions.loadImage
import com.example.tbc_android_2025.presentation.mappers.format
import com.example.tbc_android_2025.databinding.ItemPostBinding as Binding

class PostAdapter : BaseAdapter<PostModel, Binding>(
    inflater = Binding::inflate,
    diffCallback = object : ItemCallback<PostModel>() {
        override fun areItemsTheSame(oldPost: PostModel, newPost: PostModel) = oldPost.postDate == newPost.postDate
        override fun areContentsTheSame(oldPost: PostModel, newPost: PostModel) = oldPost == newPost
    }) {

    override fun bind(binding: Binding, item: PostModel) = with(receiver = binding) {
        with(receiver = item) {
            postAuthorImageView.loadImage(url = avatar)
            postCreationDateTimeTextView.text = postDate.format()
            authorFullNameTextView.text = authorFullName
//            binding = images
            commentsTextView.text =
                commentsTextView.context.getString(Strings.number_of_comments, commentsCount)
            likesTextView.text = likesTextView.context.getString(Strings.number_of_likes, likesCount)
            postDescriptionTextView.text = postDesc
        }
    }


    private fun layoutSinglePhoto(photo: String) {
        val img = makeImageView(url = photo).apply {
            id = View.generateViewId()
            layoutParams = ConstraintLayout.LayoutParams(0, WRAP_CONTENT).apply {
                dimensionRatio = "1:1" // or 16:9 depending on your need
                bottomToBottom = ConstraintSet.PARENT_ID
                endToEnd = ConstraintSet.PARENT_ID
                startToStart = ConstraintSet.PARENT_ID
                topToTop = ConstraintSet.PARENT_ID
            }
        }
        photosHolder.addView(img)
    }

    private fun makeImageView(url: String) = AppCompatImageView(requireContext()).apply {
        scaleType = ImageView.ScaleType.CENTER_CROP
        loadImage(url = url)
    }
}
