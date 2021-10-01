package com.example.couponsapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RelatedProductDto(
    @SerializedName("name") val name: String,
    @SerializedName("icon_url") val iconUrl: String
)
