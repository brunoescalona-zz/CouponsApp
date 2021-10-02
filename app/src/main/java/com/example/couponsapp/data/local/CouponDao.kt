package com.example.couponsapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.couponsapp.data.local.entities.CouponEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CouponDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(coupon: CouponEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg coupon: CouponEntity)

    @Query("SELECT * FROM coupons")
    fun getAll(): Flow<List<CouponEntity>>
}