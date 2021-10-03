package com.example.couponsapp.domain

import com.example.couponsapp.domain.models.Coupon
import kotlinx.coroutines.flow.Flow

interface CouponRepository {

    fun getAll(): Flow<Result<List<Coupon>>>

    fun get(couponId: Long): Flow<Coupon?>

    suspend fun changeState(couponId: Long)
}