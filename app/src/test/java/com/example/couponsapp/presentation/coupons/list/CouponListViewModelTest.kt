package com.example.couponsapp.presentation.coupons.list

import com.example.couponsapp.data.FakeCouponRepositoryImplementation
import com.example.couponsapp.domain.CouponRepository
import com.example.couponsapp.domain.mock.CouponsMock
import com.example.couponsapp.domain.models.State
import com.example.couponsapp.domain.use_cases.ChangeCouponState
import com.example.couponsapp.domain.use_cases.GetValidCouponsByDate
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CouponListViewModelTest {

    private lateinit var couponRepository: CouponRepository
    private lateinit var getValidCouponsByDate: GetValidCouponsByDate
    private lateinit var changeCouponState: ChangeCouponState

    private lateinit var viewModel: CouponListViewModel

    @Before
    fun setUp() {
        couponRepository = FakeCouponRepositoryImplementation()
        getValidCouponsByDate = GetValidCouponsByDate(couponRepository)
        changeCouponState = ChangeCouponState(couponRepository)

        viewModel = CouponListViewModel(getValidCouponsByDate, changeCouponState)
    }

    @Test
    fun `when initialize the view model then get loading and ready state`() = runBlocking {
        val uiStateList = viewModel.uiState.take(2).toList()

        assertThat(uiStateList[0]).isEqualTo(CouponListUiState.Loading)
        assertThat(uiStateList[1]).isInstanceOf(CouponListUiState.Ready::class.java)
        assertThat((uiStateList[1] as CouponListUiState.Ready).activeCoupons)
            .isEqualTo(0)
        assertThat((uiStateList[1] as CouponListUiState.Ready).coupons)
            .isEqualTo(CouponsMock.ordered)
    }

    @Test
    fun `when change state action is triggered then get 1 active coupon`() = runBlocking {
        launch {
            delay(10)
            changeCouponState(0)
        }
        viewModel.uiState
            .drop(2)
            .take(1)
            .collect { uiState ->
                assertThat((uiState as CouponListUiState.Ready).activeCoupons).isEqualTo(1)
                assertThat(uiState.coupons.find { it.id == 0L }?.state).isEqualTo(State.Enabled)
            }
    }

}