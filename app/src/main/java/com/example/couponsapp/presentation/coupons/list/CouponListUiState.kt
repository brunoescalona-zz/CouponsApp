package com.example.couponsapp.presentation.coupons.list

import com.example.couponsapp.domain.models.Coupon

sealed class CouponListUiState {
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