package com.example.couponsapp.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class RelatedProductEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "icon_url") val iconUrl: String
)
