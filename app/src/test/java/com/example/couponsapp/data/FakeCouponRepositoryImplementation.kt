package com.example.couponsapp.data

import com.example.couponsapp.domain.CouponRepository
import com.example.couponsapp.domain.mock.CouponsMock
import com.example.couponsapp.domain.models.Coupon
import com.example.couponsapp.domain.models.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

class FakeCouponRepositoryImplementation : CouponRepository {

    private val couponStateFlow = MutableStateFlow(Result.success(CouponsMock.list))

    override fun getAll(): Flow<Result<List<Coupon>>> = couponStateFlow

    override fun get(couponId: Long): Flow<Coupon?> {
        return couponStateFlow
            .map { result ->
                result.getOrNull()?.find { it.id == couponId }
            }
    }

    override fun changeState(couponId: Long) {
        val enabledCoupon = CouponsMock.list.find { it.id == couponId }
            ?.copy(state = State.Enabled)

        val couponList = CouponsMock.list
            .filter { it.id != couponId }
            .plus(enabledCoupon)
            .filterNotNull()

        runBlocking {
            couponStateFlow.emit(Result.success(couponList))
        }
    }
}