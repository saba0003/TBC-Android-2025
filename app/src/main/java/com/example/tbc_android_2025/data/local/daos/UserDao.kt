package com.example.tbc_android_2025.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tbc_android_2025.data.local.models.UserEntity
import kotlinx.coroutines.flow.Flow

// TODO: strings are hardcoded
@Dao
interface UserDao {

    @Query(value = "SELECT * FROM users")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<UserEntity>): List<Long>

    @Query(value = "DELETE FROM users")
    suspend fun clearUsers(): Int

}
