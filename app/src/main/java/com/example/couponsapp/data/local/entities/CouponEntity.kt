package com.example.couponsapp.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coupons")
data class CouponEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    @ColumnInfo(name = "state") val state: StateEntity,
    @ColumnInfo(name = "end_date") val date: String,
    @Embedded(prefix = "limits") val limits: LimitsEntity,
    @Embedded(prefix = "discount") val discount: DiscountEntity,
    @ColumnInfo(name = "related_products") val relatedProducts: List<RelatedProductEntity>?,
    @ColumnInfo(name = "product_code") val productCode: Long,
    @ColumnInfo(name = "conditions") val conditions: String
)