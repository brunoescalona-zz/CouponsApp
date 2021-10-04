package com.example.couponsapp.presentation.coupons.list

sealed class CouponListUiAction {
    data class NavigateToDetail(val couponId: Long) : CouponListUiAction()
}