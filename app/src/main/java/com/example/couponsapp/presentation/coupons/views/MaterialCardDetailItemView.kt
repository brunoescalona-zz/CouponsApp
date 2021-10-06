package com.example.couponsapp.presentation.coupons.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.example.couponsapp.databinding.MaterialCardDetailItemViewBinding
import com.google.android.material.card.MaterialCardView

class MaterialCardDetailItemView : MaterialCardView {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val ui = MaterialCardDetailItemViewBinding.inflate(LayoutInflater.from(context), this)

    fun setText(title: String, description: String) {
        ui.title.text = title
        ui.description.text = description
    }
}