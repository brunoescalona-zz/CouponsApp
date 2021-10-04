package com.example.couponsapp.presentation.coupons.list

import com.example.couponsapp.domain.models.Coupon
import com.example.couponsapp.presentation.UiState

sealed class CouponListUiState : UiState {
    object Loading : CouponListUiState()
    object Error : CouponListUiState()
    object Empty : CouponListUiState()
    data class Ready(
        val activeCoupons: Int,
        val coupons: List<Coupon>,
        val couponClick: (Long) -> Unit,
        val stateCouponClick: (Long) -> Unit
    ) : CouponListUiState()
}