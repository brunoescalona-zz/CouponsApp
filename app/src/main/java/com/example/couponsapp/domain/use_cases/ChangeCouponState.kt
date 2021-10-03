package com.example.couponsapp.domain.use_cases

import com.example.couponsapp.domain.CouponRepository
import javax.inject.Inject

class ChangeCouponState @Inject constructor(
    private val repository: CouponRepository
) {
    suspend operator fun invoke(couponId: Long) {
        return repository.changeState(couponId)
    }
}