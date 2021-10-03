package com.example.couponsapp.data.local

import com.example.couponsapp.data.local.entities.CouponEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCouponDaoImplementation : CouponDao {

    private val cachedCoupons = arrayListOf<CouponEntity>()

    override fun insert(coupon: CouponEntity) {
        cachedCoupons.add(coupon)
    }

    override fun insertAll(vararg coupon: CouponEntity) {
        coupon.forEach { cachedCoupons.add(it) }
    }

    override fun getAll(): Flow<List<CouponEntity>> = flow {
        emit(cachedCoupons)
    }
}