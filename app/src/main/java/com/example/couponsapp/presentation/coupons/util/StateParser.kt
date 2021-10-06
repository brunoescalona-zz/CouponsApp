package com.example.couponsapp.presentation.coupons.util

import com.example.couponsapp.R
import com.example.couponsapp.domain.models.State

fun State.text() = when (this) {
    State.Disabled -> R.string.state_disabled
    State.Enabled -> R.string.state_enabled
    State.Pending -> R.string.state_loading
}