package com.example.couponsapp.presentation.coupons.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.view.isInvisible
import com.example.couponsapp.R
import com.example.couponsapp.databinding.MaterialStateButtonBinding
import com.example.couponsapp.domain.models.State

class MaterialStateButton : FrameLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val ui = MaterialStateButtonBinding.inflate(LayoutInflater.from(context), this)

    fun setState(state: State, onClick: () -> Unit) {
        when (state) {
            State.Disabled -> setDisabledButton(onClick)
            State.Enabled -> setEnabledButton(onClick)
            State.Pending -> setLoadingButton()
        }
    }

    private fun setEnabledButton(onClick: () -> Unit) {
        ui.progress.isInvisible = true
        ui.disabled.isInvisible = true
        ui.enabled.isInvisible = false
        ui.enabled.setText(R.string.button_enabled)
        ui.enabled.setOnClickListener { onClick() }
    }

    private fun setDisabledButton(onClick: () -> Unit) {
        ui.progress.isInvisible = true
        ui.disabled.isInvisible = false
        ui.enabled.isInvisible = true
        ui.disabled.setText(R.string.button_enable)
        ui.disabled.setOnClickListener { onClick() }
    }

    private fun setLoadingButton() {
        ui.progress.isInvisible = false
        ui.disabled.isInvisible = true
        ui.enabled.isInvisible = false
        ui.enabled.text = null
        ui.enabled.setOnClickListener { }
    }
}