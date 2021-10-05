package com.example.couponsapp.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow

abstract class BaseViewModel<US : UiState> : ViewModel() {

    abstract val uiState: Flow<US>
}