package com.example.couponsapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<US : UiState> : ViewModel() {

    abstract val uiState: LiveData<US>
}