package com.example.tbc_android_2025.di.module

import com.example.tbc_android_2025.data.ProcessPhotoUseCaseImpl
import com.example.tbc_android_2025.domain.use_case.local.ProcessPhotoUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface PhotoModule {

    @Binds
    fun bindProcessPhotoUseCase(impl: ProcessPhotoUseCaseImpl): ProcessPhotoUseCase

}
