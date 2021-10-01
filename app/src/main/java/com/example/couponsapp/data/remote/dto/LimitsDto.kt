package com.example.couponsapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class LimitsDto(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String
)
