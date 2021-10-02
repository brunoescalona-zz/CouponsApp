package com.example.couponsapp.data.local.converters

import androidx.room.TypeConverter
import com.example.couponsapp.data.local.entities.StateEntity

class StateConverter {
    @TypeConverter
    fun fromEnum(enum: StateEntity): String {
        return when (enum) {
            StateEntity.Enabled -> ENABLED
            StateEntity.Disabled -> DISABLED
            StateEntity.Pending -> PENDING
        }
    }

    @TypeConverter
    fun fromInt(value: String): StateEntity {
        return when (value) {
            ENABLED -> StateEntity.Enabled
            DISABLED -> StateEntity.Disabled
            PENDING -> StateEntity.Pending
            else -> StateEntity.Disabled
        }
    }

    companion object {
        private const val ENABLED = "enabled"
        private const val DISABLED = "disabled"
        private const val PENDING = "pending"
    }
}