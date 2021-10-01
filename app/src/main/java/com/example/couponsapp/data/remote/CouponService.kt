package com.example.couponsapp.data.remote

import com.example.couponsapp.data.remote.dto.CouponDto
import com.example.couponsapp.data.remote.dto.CouponsResponseDto
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface CouponService {

    @GET("coupons")
    suspend fun getCoupons(): CouponsResponseDto

    @PUT("coupons/{couponId}/state")
    suspend fun putCouponState(@Path("couponId") couponId: Long): CouponDto
}