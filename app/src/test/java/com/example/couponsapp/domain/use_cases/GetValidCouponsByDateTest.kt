package com.example.couponsapp.domain.use_cases

import com.example.couponsapp.data.FakeCouponRepositoryImplementation
import com.example.couponsapp.domain.mock.CouponsMock
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetValidCouponsByDateTest {

    private val couponRepository = FakeCouponRepositoryImplementation()

    @Test
    fun `when get valid coupon by date is invoked then get valid and ordered coupons`() =
        runBlocking {
            val getValidCouponsByDateUseCase = GetValidCouponsByDate(couponRepository)

            getValidCouponsByDateUseCase()
                .take(1)
                .collect { result ->
                    assertThat(result.getOrNull()).isEqualTo(CouponsMock.ordered)
                }
        }
}