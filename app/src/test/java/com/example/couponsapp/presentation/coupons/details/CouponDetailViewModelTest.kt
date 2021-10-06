package com.example.couponsapp.presentation.coupons.details

import androidx.lifecycle.SavedStateHandle
import com.example.couponsapp.data.FakeCouponRepositoryImplementation
import com.example.couponsapp.domain.CouponRepository
import com.example.couponsapp.domain.mock.CouponsMock
import com.example.couponsapp.domain.models.State
import com.example.couponsapp.domain.use_cases.ChangeCouponState
import com.example.couponsapp.domain.use_cases.GetCoupon
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CouponDetailViewModelTest {

    private lateinit var couponRepository: CouponRepository
    private lateinit var getCoupon: GetCoupon
    private lateinit var changeCouponState: ChangeCouponState
    private lateinit var savedStateHandle: SavedStateHandle

    private lateinit var viewModel: CouponDetailViewModel

    @Before
    fun setUp() {
        couponRepository = FakeCouponRepositoryImplementation()
        getCoupon = GetCoupon(couponRepository)
        changeCouponState = ChangeCouponState(couponRepository)
        savedStateHandle = SavedStateHandle()
        savedStateHandle.set("coupon_id", 0L)

        viewModel = CouponDetailViewModel(
            getCoupon,
            changeCouponState,
            savedStateHandle
        )
    }

    @Test
    fun `when initialize the view model then get the coupon`() = runBlocking {
        viewModel.uiState
            .drop(1)
            .take(1)
            .collect {
                assertThat((it as CouponDetailUiState.Ready).coupon).isEqualTo(CouponsMock.coupon0)
            }
    }

    @Test
    fun `when change state action is triggered then get the state should change`() = runBlocking {
        launch {
            delay(10)
            changeCouponState(0)
        }
        viewModel.uiState
            .drop(2)
            .take(1)
            .collect {
                assertThat((it as CouponDetailUiState.Ready).coupon.state).isEqualTo(State.Enabled)
            }
    }
}