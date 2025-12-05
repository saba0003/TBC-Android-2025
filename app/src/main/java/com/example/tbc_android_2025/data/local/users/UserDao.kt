package com.example.tbc_android_2025.data.local.users

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tbc_android_2025.data.local.users.UserTable.DELETE_ALL
import com.example.tbc_android_2025.data.local.users.UserTable.SELECT_ALL
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query(value = SELECT_ALL)
    fun getAllUsers(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<UserEntity>)

    @Query(value = DELETE_ALL)
    suspend fun clearUsers()

}
