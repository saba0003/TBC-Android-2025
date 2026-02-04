package com.example.data.di.module

import com.example.data.BuildConfig.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val IGNORE_KEYS_NOT_PRESENT_IN_DTO = true
    private const val USE_DEFAULT_VALUES_SHOULD_A_KEY_BE_MISSING = true
    private const val NULL_VALUES_IN_JSON_MUST_BE_HANDLED_CORRECTLY = false
    private const val CONTENT_TYPE = "application/json"

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor = loggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = IGNORE_KEYS_NOT_PRESENT_IN_DTO
        coerceInputValues = USE_DEFAULT_VALUES_SHOULD_A_KEY_BE_MISSING
        explicitNulls = NULL_VALUES_IN_JSON_MUST_BE_HANDLED_CORRECTLY
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, json: Json): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType = CONTENT_TYPE.toMediaType()))
            .client(okHttpClient)
            .build()

}
