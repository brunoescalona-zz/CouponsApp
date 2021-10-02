package com.example.couponsapp.domain.use_cases

import com.example.couponsapp.domain.CouponRepository
import com.example.couponsapp.domain.models.Coupon
import kotlinx.coroutines.flow.Flow

class GetCoupon(
    private val repository: CouponRepository
) {
    operator fun invoke(couponId: Long): Flow<Coupon?> {
        return repository.get(couponId)
    }
}