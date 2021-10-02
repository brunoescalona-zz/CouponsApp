package com.example.couponsapp.data.local.entities

import androidx.room.ColumnInfo

data class LimitsEntity(
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String
)
