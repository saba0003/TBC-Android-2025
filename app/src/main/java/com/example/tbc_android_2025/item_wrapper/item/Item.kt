package com.example.tbc_android_2025.item_wrapper.item

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Item(
    var field_id: Int = nextId(),
    val hint: String,
    val field_type: FieldType,
    val keyboard: KeyboardType? = null,
    val required: Boolean,
    val is_active: Boolean,
    val icon: String
) {
    companion object {
        private var counter = 0
        private fun nextId(): Int = ++counter
    }
}
