package com.example.couponsapp.data.remote.mappers

import com.example.couponsapp.data.remote.mock.CouponDtosTestMock
import com.example.couponsapp.domain.mock.CouponsMock
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class DtoMappersTest {

    @Test
    fun `when coupon dto is mapped to domain then return domain model`() {
        val couponDto = CouponDtosTestMock.coupon0

        val couponMapped = couponDto.toDomain()

        assertThat(couponMapped).isEqualTo(CouponsMock.coupon0)
    }
}