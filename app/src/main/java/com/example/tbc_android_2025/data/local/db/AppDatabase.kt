package com.example.tbc_android_2025.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tbc_android_2025.data.local.users.UserEntity
import com.example.tbc_android_2025.data.local.users.UserDao

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}
