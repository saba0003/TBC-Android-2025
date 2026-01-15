package com.example.tbc_android_2025.presentation.common

import android.view.LayoutInflater as Inflater
import android.view.ViewGroup as Container
import com.example.tbc_android_2025.R as Resources

typealias ViewBindingInflater<VB> = (Inflater, Container?, Boolean) -> VB
typealias Drawables = Resources.drawable
typealias Images = Resources.mipmap
typealias Strings = Resources.string
typealias Fonts = Resources.font
