package com.example.couponsapp.data.remote

import com.example.couponsapp.data.remote.dto.CouponDto
import com.example.couponsapp.data.remote.dto.CouponsResponseDto
import com.example.couponsapp.data.remote.mock.CouponDtosTestMock

class FakeCouponServiceImplementation : CouponService {

    override suspend fun getCoupons() = CouponsResponseDto(
        coupons = CouponDtosTestMock.list
    )

    override suspend fun putCouponState(couponId: Long): CouponDto {
        return CouponDtosTestMock.list.find { it.id == couponId }
            ?: CouponDtosTestMock.coupon0
    }
}