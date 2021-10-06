package com.example.couponsapp.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<US : UiState> : ViewModel() {

    abstract val uiState: StateFlow<US>
}