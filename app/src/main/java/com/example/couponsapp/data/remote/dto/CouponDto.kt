package com.example.couponsapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CouponDto(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("is_enabled") val isEnabled: Boolean,
    @SerializedName("end_date") val date: String,
    @SerializedName("limits") val limits: LimitsDto,
    @SerializedName("discount") val discount: DiscountDto,
    @SerializedName("related_products") val relatedProducts: List<RelatedProductDto>?,
    @SerializedName("product_code") val productCode: Long,
    @SerializedName("conditions") val conditions: String
)
