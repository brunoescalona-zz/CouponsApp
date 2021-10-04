package com.example.couponsapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<US : UiState, VM : BaseViewModel<US>> : AppCompatActivity() {

    protected abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.uiState.observe(this, { state -> onUiState(state) })
    }

    protected abstract fun onUiState(state: US)
}