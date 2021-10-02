package com.example.couponsapp.data.local.entities

sealed class StateEntity {
    object Enabled : StateEntity()
    object Disabled : StateEntity()
    object Pending : StateEntity()
}