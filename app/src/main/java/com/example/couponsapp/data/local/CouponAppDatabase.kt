package com.example.couponsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.couponsapp.data.local.entities.CouponEntity

@Database(entities = [CouponEntity::class], version = 1)
abstract class CouponAppDatabase : RoomDatabase() {
    abstract fun couponDao(): CouponDao
}