package com.example.couponsapp.di

import com.example.couponsapp.data.CouponRepositoryImplementation
import com.example.couponsapp.domain.CouponRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCouponRepository(
        couponRepositoryImplementation: CouponRepositoryImplementation
    ): CouponRepository
}