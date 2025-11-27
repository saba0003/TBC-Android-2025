package com.example.tbc_android_2025.data.commons

import android.content.Context
import androidx.core.content.ContextCompat
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StringProvider @Inject constructor(private val context: Context) {

    fun getString(resId: Int) = ContextCompat.getString(context, resId)

}
