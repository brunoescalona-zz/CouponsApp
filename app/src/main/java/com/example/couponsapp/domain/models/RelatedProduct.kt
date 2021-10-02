package com.example.couponsapp.domain.models

import androidx.annotation.DrawableRes

data class RelatedProduct(
    val id: Long,
    val name: String,
    @DrawableRes val icon: Int
)
