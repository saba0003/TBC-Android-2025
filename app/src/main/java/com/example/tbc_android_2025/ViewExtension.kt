package com.example.tbc_android_2025

import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

fun View.popMessage(resId: Int, color: Int? = null) {
    val snackbar = Snackbar.make(this, context.getString(resId), Snackbar.LENGTH_SHORT)
    color?.let { snackbar.setBackgroundTint(ContextCompat.getColor(context, it)) }
    snackbar.show()
}

fun View.popMessage(text: String, color: Int? = null) {
    val snackbar = Snackbar.make(this, text, Snackbar.LENGTH_SHORT)
    color?.let { snackbar.setBackgroundTint(ContextCompat.getColor(context, it)) }
    snackbar.show()
}

//fun View.popMessage(resId: Int, color: Int? = null) {
//    val snackbar = Snackbar.make(this, context.getString(resId), Snackbar.LENGTH_SHORT)
//    color?.let {
//        snackbar.setBackgroundTint(ContextCompat.getColor(context, it))
//        snackbar.setTextColor(ContextCompat.getColor(context, android.R.color.white))
//    }
//    snackbar.show()
//}
