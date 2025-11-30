package com.example.tbc_android_2025.domain.commons

import android.content.Context
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

// TODO: too many files in commons package
// TODO: should it be singleton?
class StringProvider @Inject constructor(@field:ApplicationContext private val context: Context) : ResourceProvider {

    override fun getString(resId: Int) = ContextCompat.getString(context, resId)

}
