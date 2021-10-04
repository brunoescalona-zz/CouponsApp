package com.example.couponsapp.presentation.coupons.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asLiveData
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

    override val uiState: LiveData<CouponDetailUiState> = getCoupon(couponId = couponId)
        .map { coupon ->
            if (coupon == null) throw IllegalStateException()
            CouponDetailUiState(
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
        .asLiveData()

    companion object {
        private const val TAG = "CouponDetailViewModel"
    }
}