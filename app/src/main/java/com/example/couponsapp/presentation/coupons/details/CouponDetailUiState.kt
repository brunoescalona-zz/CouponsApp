package com.example.couponsapp.presentation.coupons.details

import com.example.couponsapp.domain.models.Coupon
import com.example.couponsapp.presentation.UiState

data class CouponDetailUiState(
    val coupon: Coupon,
    val stateCouponClick: () -> Unit
) : UiState
