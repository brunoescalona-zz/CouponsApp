package com.example.couponsapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CouponsResponseDto(
    @SerializedName("coupons") val coupons: List<CouponDto>
)
