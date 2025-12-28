package com.example.tbc_android_2025.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tbc_android_2025.data.local.entity.TemplateEntity
import com.example.tbc_android_2025.data.local.entity.TemplateEntity.Companion.TABLE_NAME

@Dao
interface TemplateDao {

    @Query(value = SELECT_ALL)
    suspend fun getTemplateEntities(): List<TemplateEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(templateEntity: TemplateEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(templateEntities: List<TemplateEntity>)

    @Query(value = DELETE_ALL)
    suspend fun clear()

    private companion object {
        const val SELECT_ALL = "SELECT * FROM $TABLE_NAME"
        const val DELETE_ALL = "DELETE FROM $TABLE_NAME"
    }
}
