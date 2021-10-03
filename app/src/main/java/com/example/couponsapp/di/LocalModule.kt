package com.example.couponsapp.di

import android.content.Context
import androidx.room.Room
import com.example.couponsapp.data.local.CouponAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): CouponAppDatabase {
        return Room.databaseBuilder(
            context,
            CouponAppDatabase::class.java,
            "coupon_database"
        ).build()
    }

    @Provides
    @Singleton
    fun providesCouponDao(database: CouponAppDatabase) = database.couponDao()
}