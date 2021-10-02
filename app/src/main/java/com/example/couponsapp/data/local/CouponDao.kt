package com.example.couponsapp.data.local

import androidx.room.Insert
import androidx.room.Query
import com.example.couponsapp.data.local.entities.CouponEntity
import kotlinx.coroutines.flow.Flow

interface CouponDao {
    @Insert
    fun insert(coupon: CouponEntity)

    @Insert
    fun insertAll(vararg coupon: CouponEntity)

    @Query("SELECT * FROM coupons")
    fun getAll(): Flow<List<CouponEntity>>
}