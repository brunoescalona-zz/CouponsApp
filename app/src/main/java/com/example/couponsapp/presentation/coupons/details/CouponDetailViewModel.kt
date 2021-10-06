package com.example.couponsapp.presentation.coupons.details

import android.graphics.Color
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.couponsapp.domain.use_cases.ChangeCouponState
import com.example.couponsapp.domain.use_cases.GetCoupon
import com.example.couponsapp.presentation.BaseViewModel
import com.example.couponsapp.presentation.coupons.details.CouponDetailActivity.Companion.COUPON_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CouponDetailViewModel @Inject constructor(
    getCoupon: GetCoupon,
    changeCouponState: ChangeCouponState,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel<CouponDetailUiState>() {

    private val couponId by lazy {
        savedStateHandle.get<Long>(COUPON_ID) ?: throw IllegalStateException()
    }
    private val scrollState = MutableStateFlow(0)

    override val uiState: StateFlow<CouponDetailUiState> = combine(
        getCoupon(couponId = couponId),
        scrollState
    ) { coupon, scrollPosition ->
        if (coupon == null) throw IllegalStateException()
        CouponDetailUiState.Ready(
            toolbarTitle = if (scrollPosition == 0) coupon.title else "",
            toolbarColor = if (scrollPosition == 0) Color.WHITE else Color.TRANSPARENT,
            coupon = coupon,
            stateCouponClick = {
                Log.d(TAG, "state click coupon with id ${coupon.id}")
                viewModelScope.launch(Dispatchers.Default) { changeCouponState(coupon.id) }
            }
        )
    }
        .distinctUntilChanged()
        .flowOn(Dispatchers.Default)
        .onEach { Log.d(TAG, "ui state changed to $it") }
        .catch { Log.e(TAG, "error in the ui state flow with $it") }
        .stateIn(viewModelScope, SharingStarted.Lazily, CouponDetailUiState.Loading)

    fun updateScroll(scroll: Int) {
        viewModelScope.launch { scrollState.emit(scroll) }
    }

    companion object {
        private const val TAG = "CouponDetailViewModel"
    }
}