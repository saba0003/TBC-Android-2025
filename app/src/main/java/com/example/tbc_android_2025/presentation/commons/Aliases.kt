package com.example.tbc_android_2025.presentation.commons

import android.view.LayoutInflater as Inflater
import android.view.ViewGroup as Container

typealias ViewBindingInflater<VB> = (Inflater, Container?, Boolean) -> VB
