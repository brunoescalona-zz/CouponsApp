package com.example.couponsapp.presentation.coupons.details

import androidx.annotation.ColorInt
import com.example.couponsapp.domain.models.Coupon
import com.example.couponsapp.presentation.UiState

data class CouponDetailUiState(
    val toolbarTitle: String,
    @ColorInt val toolbarColor: Int,
    val coupon: Coupon,
    val stateCouponClick: () -> Unit
) : UiState