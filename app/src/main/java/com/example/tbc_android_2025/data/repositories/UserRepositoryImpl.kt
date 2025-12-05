package com.example.tbc_android_2025.data.repositories

import android.Manifest.permission.INTERNET
import android.content.Context
import android.content.pm.PackageManager.PERMISSION_GRANTED
import androidx.core.content.ContextCompat
import com.example.tbc_android_2025.commons.Strings
import com.example.tbc_android_2025.data.local.data_sources.LocalUserDataSource
import com.example.tbc_android_2025.data.mappers.toDomain
import com.example.tbc_android_2025.data.mappers.toEntities
import com.example.tbc_android_2025.data.remote.data_sources.RemoteUserDataSource
import com.example.tbc_android_2025.domain.commons.Resource
import com.example.tbc_android_2025.domain.commons.Resource.*
import com.example.tbc_android_2025.domain.models.UsersModel
import com.example.tbc_android_2025.domain.repositories.UserRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    @param:ApplicationContext private val context: Context,
    private val remote: RemoteUserDataSource,
    private val local: LocalUserDataSource
) : UserRepository {

    override fun getUsers(): Flow<Resource<UsersModel>> = flow {
        emit(value = Loader(isLoading = true))
        if (hasInternetPermission())
            fetchFromRemote()
        else
            fetchFromLocal()
    }


    /** ======================================= FETCHERS ======================================== */
    private suspend fun FlowCollector<Resource<UsersModel>>.fetchFromRemote() {
        remote.getUsers().collect {
            when (it) {
                is Success -> {
                    local.clearUsers()
                    local.saveUsers(users = it.data.toEntities())
                    emit(value = Success(data = it.data.toDomain()))
                }
                is Error -> emit(value = Error(errorMessage = it.errorMessage))
                is Loader -> Unit
            }
        }
    }

    private suspend fun FlowCollector<Resource<UsersModel>>.fetchFromLocal() {
        local.getUsers().collect {
            if (it.isNotEmpty())
                emit(value = Success(data = it.toDomain()))
            else
                emit(
                    value = Error(
                        errorMessage = ContextCompat.getString(
                            context, Strings.error_no_cached_data_available
                        )
                    )
                )
        }
    }
    /** ========================================================================================= */


    /** ========================================== AUX ========================================== */
    private fun hasInternetPermission() =
        ContextCompat.checkSelfPermission(context, INTERNET) == PERMISSION_GRANTED
    /** ========================================================================================= */
}
