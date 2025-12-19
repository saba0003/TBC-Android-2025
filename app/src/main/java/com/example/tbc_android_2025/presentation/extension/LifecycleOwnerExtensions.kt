package com.example.tbc_android_2025.presentation.extension

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun LifecycleOwner.launchAndRepeatOnStart(block: suspend CoroutineScope.() -> Unit) =
    lifecycleScope.launch { repeatOnLifecycle(state = Lifecycle.State.STARTED, block = block) }
