package com.example.tbc_android_2025.data.network

object NetworkConstants {

    const val HEADER_KEY = "x-api-key"
    const val HEADER_VALUE = "reqres-free-v1"
    private const val PATH = "/api/"
    const val BASE_URL = "https://reqres.in$PATH"
    const val REGISTER_ENDPOINT = "register"
    const val LOGIN_ENDPOINT = "login"
    const val USERS_ENDPOINT = "users"
    const val PAGE_QUERY_PARAMETER = "page"
    const val AUTHORIZATION = "Authorization"
    const val BEARER_TOKEN = "Bearer %s"
    const val PREF_NAME = "session_pref"
    const val AUTH_TOKEN = "auth_token"

}
