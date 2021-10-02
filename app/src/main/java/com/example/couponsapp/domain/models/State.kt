package com.example.couponsapp.domain.models

sealed class State {
    object Enabled : State()
    object Disabled : State()
    object Pending : State()
}
