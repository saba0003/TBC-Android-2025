package com.example.tbc_android_2025.data.local.entity

import androidx.room.Entity
import com.example.tbc_android_2025.data.local.entity.TemplateEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class TemplateEntity(val id: Int) {
    companion object {
        const val TABLE_NAME = "templates"
    }
}
