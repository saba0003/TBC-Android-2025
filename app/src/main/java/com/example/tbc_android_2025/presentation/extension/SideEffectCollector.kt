package com.example.tbc_android_2025.presentation.extension

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow

@Composable
fun <T> Flow<T>.CollectSideEffect(
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED, action: suspend (T) -> Unit
) = with(receiver = LocalLifecycleOwner.current) {
    LaunchedEffect(key1 = this@CollectSideEffect, key2 = this@with) {
        lifecycle.repeatOnLifecycle(state = lifecycleState) {
            this@CollectSideEffect.collect { action(it) }
        }
    }
}
