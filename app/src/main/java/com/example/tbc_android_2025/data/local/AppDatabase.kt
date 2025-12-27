package com.example.tbc_android_2025.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tbc_android_2025.data.local.dao.TemplateDao
import com.example.tbc_android_2025.data.local.entity.TemplateEntity

@Database(entities = [TemplateEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun templateDao(): TemplateDao

}
