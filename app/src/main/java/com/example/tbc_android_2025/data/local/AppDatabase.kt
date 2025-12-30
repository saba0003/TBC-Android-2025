package com.example.tbc_android_2025.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tbc_android_2025.data.local.dao.EquipmentCategoryDao
import com.example.tbc_android_2025.data.local.entity.EquipmentCategoryEntity

@Database(entities = [EquipmentCategoryEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun equipmentCategoryDao(): EquipmentCategoryDao

}
