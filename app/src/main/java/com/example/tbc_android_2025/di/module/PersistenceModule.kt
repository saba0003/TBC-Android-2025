package com.example.tbc_android_2025.di.module

import android.content.Context
import androidx.room.Room
import com.example.tbc_android_2025.data.local.AppDatabase
import com.example.tbc_android_2025.data.local.dao.EquipmentCategoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    private const val DATABASE_NAME = "equipment_category_database"
    private const val DROP_ALL_TABLES = false


    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context = context, klass = AppDatabase::class.java, name = DATABASE_NAME
        ).fallbackToDestructiveMigration(dropAllTables = DROP_ALL_TABLES).build()

    @Provides
    @Singleton
    fun provideEquipmentCategoryDao(appDatabase: AppDatabase): EquipmentCategoryDao =
        appDatabase.equipmentCategoryDao()

}
