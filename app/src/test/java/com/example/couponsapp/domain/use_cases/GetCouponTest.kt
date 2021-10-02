package com.example.couponsapp.domain.use_cases

import com.example.couponsapp.data.FakeCouponRepositoryImplementation
import com.example.couponsapp.domain.mock.CouponsMock
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetCouponTest {

    private val couponRepository = FakeCouponRepositoryImplementation()

    @Test
    fun `when get coupon by id invoked then get the coupon`() = runBlocking {
        val getGetCouponUseCase = GetCoupon(couponRepository)

        getGetCouponUseCase(3)
            .take(1)
            .collect { coupon ->
                assertThat(coupon).isEqualTo(CouponsMock.coupon3)
            }
    }
}