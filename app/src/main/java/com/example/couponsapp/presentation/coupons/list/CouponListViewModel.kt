package com.example.couponsapp.presentation.coupons.list

import android.util.Log
import androidx.lifecycle.*
import com.example.couponsapp.domain.models.State
import com.example.couponsapp.domain.use_cases.ChangeCouponState
import com.example.couponsapp.domain.use_cases.GetValidCouponsByDate
import com.example.couponsapp.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CouponListViewModel @Inject constructor(
    getValidCouponsByDate: GetValidCouponsByDate,
    changeCouponState: ChangeCouponState
) : BaseViewModel<CouponListUiState>() {

    val uiAction = MutableSharedFlow<CouponListUiAction?>()

    override val uiState: LiveData<CouponListUiState> = getValidCouponsByDate()
        .map { result ->
            if (result.isFailure) return@map CouponListUiState.Error
            val coupons = result.getOrNull()
            if (coupons == null || coupons.isEmpty()) return@map CouponListUiState.Empty
            CouponListUiState.Ready(
                activeCoupons = coupons.filter { it.state == State.Enabled }.count(),
                coupons = coupons,
                couponClick = {
                    Log.d(TAG, "coupon click with id $it")
                    viewModelScope.launch(Dispatchers.Default) {
                        uiAction.emit(CouponListUiAction.NavigateToDetail(it))
                    }
                },
                stateCouponClick = {
                    Log.d(TAG, "state click coupon with id $it")
                    viewModelScope.launch(Dispatchers.Default) { changeCouponState(it) }
                }
            )
        }
        .onStart { emit(CouponListUiState.Loading) }
        .distinctUntilChanged()
        .flowOn(Dispatchers.Default)
        .onEach { Log.d(TAG, "ui state changed to $it") }
        .catch { Log.e(TAG, "error in the ui state flow with $it") }
        .asLiveData()

    companion object {
        private const val TAG = "CouponListViewModel"
    }
}