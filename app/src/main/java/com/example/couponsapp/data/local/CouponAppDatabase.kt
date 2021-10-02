package com.example.couponsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.couponsapp.data.local.converters.ListConverter
import com.example.couponsapp.data.local.entities.CouponEntity

@Database(entities = [CouponEntity::class], version = 1)
@TypeConverters(ListConverter::class)
abstract class CouponAppDatabase : RoomDatabase() {
    abstract fun couponDao(): CouponDao
}