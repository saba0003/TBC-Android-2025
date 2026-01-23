package com.example.tbc_android_2025.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow

@Composable
fun <T> CollectSideEffect(flow: Flow<T>, action: suspend (T) -> Unit) =
    with(receiver = LocalLifecycleOwner.current) {
        LaunchedEffect(key1 = this, key2 = flow) {
            lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                flow.collect { action(it) }
            }
        }
    }
