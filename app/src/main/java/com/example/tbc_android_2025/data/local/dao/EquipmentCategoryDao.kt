package com.example.tbc_android_2025.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tbc_android_2025.data.local.entity.EquipmentCategoryEntity
import com.example.tbc_android_2025.data.local.entity.EquipmentCategoryEntity.Companion.TABLE_NAME

@Dao
interface EquipmentCategoryDao {

//    @Query(
//        value = "SELECT ec FROM $TABLE_NAME ec WHERE LOWER(ec.name) LIKE LOWER(CONCAT('%', :name, '%'))",
//        countQuery = "SELECT COUNT(ec) FROM $TABLE_NAME ec WHERE LOWER(ec.name) LIKE LOWER(CONCAT('%', :name, '%'))"
//    )
    @Query(SELECT_ALL)
    suspend fun findByNameContainingIgnoreCase(): List<EquipmentCategoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(equipmentCategoryEntity: EquipmentCategoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(templateEntities: List<EquipmentCategoryEntity>)

    @Query(value = DELETE_ALL)
    suspend fun clear()

    private companion object {
        const val SELECT_ALL = "SELECT * FROM $TABLE_NAME"
        const val DELETE_ALL = "DELETE FROM $TABLE_NAME"
    }
}
