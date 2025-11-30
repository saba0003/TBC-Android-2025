package com.example.tbc_android_2025.presentation.mappers

import com.example.tbc_android_2025.domain.models.requests.RegisterRequest as RegisterRequestDomain
import com.example.tbc_android_2025.presentation.screen.register.RegisterRequest as RegisterRequestPresentation
import com.example.tbc_android_2025.domain.models.requests.LogInRequest as LogInRequestDomain
import com.example.tbc_android_2025.presentation.screen.log_in.LogInRequest as LogInRequestPresentation

fun RegisterRequestPresentation.toDomain() =
    RegisterRequestDomain(email = email, password = password, repeatedPassword = repeatedPassword)

fun LogInRequestPresentation.toDomain() = LogInRequestDomain(email = email, password = password)
