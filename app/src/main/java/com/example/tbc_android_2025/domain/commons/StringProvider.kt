package com.example.tbc_android_2025.domain.commons

import android.content.Context
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

// TODO: too many files in commons package

@Singleton
class StringProvider @Inject constructor(@param:ApplicationContext private val context: Context) : ResourceProvider {

    override fun getString(resId: Int) = ContextCompat.getString(context, resId)

}
