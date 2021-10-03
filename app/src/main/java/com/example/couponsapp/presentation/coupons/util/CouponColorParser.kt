package com.example.couponsapp.presentation.coupons.util

import com.example.couponsapp.R

fun Int.toColorRes() = if (this <= 30) R.color.teal_200 else R.color.purple_500
fun Int.toDarkColorRes() = if (this <= 30) R.color.teal_700 else R.color.purple_700