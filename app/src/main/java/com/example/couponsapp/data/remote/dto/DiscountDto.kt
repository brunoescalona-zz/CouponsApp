package com.example.couponsapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class DiscountDto(
    @SerializedName("value") val value: Int,
    @SerializedName("special") val special: String?
)