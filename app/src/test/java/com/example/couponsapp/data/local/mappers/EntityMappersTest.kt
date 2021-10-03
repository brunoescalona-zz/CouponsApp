package com.example.couponsapp.data.local.mappers

import com.example.couponsapp.data.local.mock.CouponEntitiesTestMock
import com.example.couponsapp.domain.mock.CouponsMock
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class EntityMappersTest {

    @Test
    fun `when coupon entity is mapped to domain then return domain model`() {
        val couponEntity = CouponEntitiesTestMock.coupon0

        val couponMapped = couponEntity.toDomain()

        assertThat(couponMapped).isEqualTo(CouponsMock.coupon0)
    }

    @Test
    fun `when coupon domain is mapped to entity then return entity model`() {
        val coupon = CouponsMock.coupon0

        val couponMapped = coupon.toEntity()

        assertThat(couponMapped).isEqualTo(CouponEntitiesTestMock.coupon0)
    }
}