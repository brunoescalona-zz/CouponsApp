package com.example.couponsapp.data.local.entities

import androidx.room.ColumnInfo

data class DiscountEntity(
    @ColumnInfo(name = "value") val value: Int,
    @ColumnInfo(name = "special") val special: String?
)
