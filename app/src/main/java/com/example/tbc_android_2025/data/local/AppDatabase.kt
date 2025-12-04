package com.example.tbc_android_2025.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tbc_android_2025.data.local.daos.UserDao
import com.example.tbc_android_2025.data.local.models.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}
