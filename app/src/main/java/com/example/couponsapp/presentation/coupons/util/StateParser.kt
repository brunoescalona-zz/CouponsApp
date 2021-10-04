package com.example.couponsapp.presentation.coupons.util

import com.example.couponsapp.R
import com.example.couponsapp.domain.models.State
import com.google.android.material.button.MaterialButton

fun MaterialButton.styleButtonForState(couponState: State) {
    val buttonText = when (couponState) {
        State.Disabled -> R.string.button_enable
        State.Enabled -> R.string.button_enabled
        State.Pending -> R.string.button_loading
    }
    setText(buttonText)

    isEnabled = couponState != State.Pending
}

fun State.text() = when (this) {
    State.Disabled -> R.string.state_disabled
    State.Enabled -> R.string.state_enabled
    State.Pending -> R.string.state_loading
}