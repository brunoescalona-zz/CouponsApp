package com.example.couponsapp.presentation.coupons.util

import com.example.couponsapp.R


fun String.toDrawableRes(): Int {

    val imageName = this
        .split("/")
        .last()

    return when (imageName) {
        "image0" -> R.drawable.fruits_coupon
        "image1" -> R.drawable.potatoes_coupon
        "image2" -> R.drawable.fresh_vegetables_coupon
        "image3" -> R.drawable.bread_pastries_coupon
        "image4" -> R.drawable.pizza_coupon
        "icon0" -> R.drawable.strawberry_icon
        "icon1" -> R.drawable.orange_icon
        "icon2" -> R.drawable.banana_icon
        "icon3" -> R.drawable.watermelon_icon
        "icon4" -> R.drawable.apple_icon
        else -> R.drawable.ic_launcher_background
    }
}