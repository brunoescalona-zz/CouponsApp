package com.example.couponsapp.domain.models

import java.time.LocalDate

data class Coupon(
    val id: Long,
    val title: String,
    val description: String,
    val image: String,
    val state: State,
    val expiration: LocalDate,
    val limits: Limits,
    val discount: Discount,
    val relatedProducts: List<RelatedProduct>?,
    val productCode: Long,
    val conditions: String
)
