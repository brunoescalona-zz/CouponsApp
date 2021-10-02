package com.example.couponsapp.domain.use_cases

import com.example.couponsapp.data.FakeCouponRepositoryImplementation
import com.example.couponsapp.domain.models.State
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.junit.Test


class ChangeCouponStateTest {

    private val couponRepository = FakeCouponRepositoryImplementation()

    @Test
    fun `when change state is invoked for a disabled coupon then change the state to enabled`() =
        runBlocking {
            val changeCouponStateUseCase = ChangeCouponState(couponRepository)
            changeCouponStateUseCase(0)

            couponRepository.getAll()
                .take(1)
                .collect { result ->
                    val state = result.getOrNull()
                        ?.find { it.id == 0L }
                        ?.state

                    assertThat(state).isEqualTo(State.Enabled)
                }
        }
}