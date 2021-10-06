package com.example.couponsapp.presentation.coupons.details

import androidx.annotation.ColorInt
import com.example.couponsapp.domain.models.Coupon
import com.example.couponsapp.presentation.UiState

sealed class CouponDetailUiState : UiState {
    data class Ready(
        val toolbarTitle: String,
        @ColorInt val toolbarColor: Int,
        val coupon: Coupon,
        val stateCouponClick: () -> Unit
    ) : CouponDetailUiState()

    object Loading : CouponDetailUiState()
}