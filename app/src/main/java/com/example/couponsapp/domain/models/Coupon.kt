package com.example.couponsapp.domain.models

import androidx.annotation.DrawableRes
import java.time.LocalDate

data class Coupon(
    val id: Long,
    val title: String,
    val description: String,
    @DrawableRes val image: Int,
    val state: State,
    val expiration: LocalDate,
    val limits: Limits,
    val discount: Discount,
    val relatedProducts: List<RelatedProduct>?,
    val productCode: Long,
    val conditions: String
)
