package com.example.couponsapp.presentation.coupons.details

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.couponsapp.R
import com.example.couponsapp.databinding.ActivityCouponDetailBinding
import com.example.couponsapp.domain.models.Discount
import com.example.couponsapp.domain.models.Limits
import com.example.couponsapp.domain.models.State
import com.example.couponsapp.presentation.BaseActivity
import com.example.couponsapp.presentation.coupons.details.recycler.RelatedProductItemAdapter
import com.example.couponsapp.presentation.coupons.util.*
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate


@AndroidEntryPoint
class CouponDetailActivity : BaseActivity<CouponDetailUiState, CouponDetailViewModel>(),
    OnOffsetChangedListener {

    override val viewModel: CouponDetailViewModel by viewModels()
    private lateinit var ui: ActivityCouponDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityCouponDetailBinding.inflate(layoutInflater)
        setContentView(ui.root)
        setSupportActionBar(ui.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        ui.appBar.addOnOffsetChangedListener(this)
    }

    override fun onUiState(state: CouponDetailUiState) {
        Log.d(TAG, "display the detail coupon ${state.coupon}")
        ui.banner.setImageResource(state.coupon.image.toDrawableRes())
        configureDiscountBanner(state.coupon.discount)
        configureState(state.coupon.state, state.stateCouponClick)
        ui.title.text = state.coupon.title
        ui.description.text = state.coupon.description
        ui.expiration.text = getString(
            R.string.expiration_label,
            LocalDate.now().until(state.coupon.expiration).days.toString()
        )
        configureLimits(state.coupon.limits)
        state.coupon.relatedProducts?.let {
            ui.relatedProductsList.adapter = RelatedProductItemAdapter(it)
            ui.relatedProductsList.isVisible = true
            ui.relatedProductsTitle.isVisible = true
        }

        ui.productCodeValue.text = state.coupon.productCode.toString()
        ui.conditionsValue.text = state.coupon.conditions

        ui.collapsingToolbarLayout.title = state.toolbarTitle
        ui.toolbar.setBackgroundColor(state.toolbarColor)
    }

    private fun configureDiscountBanner(discount: Discount) {
        ui.discount.text = "${discount.value}%"
        discount.special?.let {
            ui.discountSpecial.text = it
            ui.discountSpecial.isVisible = true
        }
        val color = ContextCompat.getColor(this, discount.value.toColorRes())
        val darkColor = ContextCompat.getColor(this, discount.value.toDarkColorRes())
        ui.discount.setBackgroundColor(color)
        ui.discountText.setBackgroundColor(color)
        ui.discountSpecial.setBackgroundColor(darkColor)
    }

    private fun configureState(
        state: State,
        onClick: () -> Unit
    ) {
        ui.stateButton.styleButtonForState(state)
        ui.stateButton.setOnClickListener { onClick() }
        ui.stateText.setText(state.text())
    }

    private fun configureLimits(limits: Limits) {
        ui.limitTitle.text = limits.title
        ui.limitDescription.text = limits.description
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        viewModel.updateScroll(appBarLayout.totalScrollRange + verticalOffset)
    }

    companion object {
        private const val TAG = "CouponDetailActivity"

        const val COUPON_ID = "coupon_id"
        fun createIntent(fromActivity: Activity, couponId: Long): Intent {
            return Intent(fromActivity, CouponDetailActivity::class.java).apply {
                putExtra(COUPON_ID, couponId)
            }
        }
    }
}