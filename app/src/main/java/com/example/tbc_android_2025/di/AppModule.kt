package com.example.tbc_android_2025.di

import com.example.tbc_android_2025.BuildConfig
import com.example.tbc_android_2025.domain.validation.ValidatePasscodeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PasscodeModule {

    @Provides
    @Singleton
    fun provideValidatePasscodeUseCase(): ValidatePasscodeUseCase =
        ValidatePasscodeUseCase(correctPasscode = BuildConfig.PASSCODE)

}
