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
import com.example.couponsapp.presentation.BaseActivity
import com.example.couponsapp.presentation.coupons.details.recycler.RelatedProductItemAdapter
import com.example.couponsapp.presentation.coupons.util.text
import com.example.couponsapp.presentation.coupons.util.toCouponBannerDrawable
import com.example.couponsapp.presentation.coupons.util.toDarkColorRes
import com.example.couponsapp.presentation.coupons.util.toDrawableRes
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
        when (state) {
            CouponDetailUiState.Loading -> Log.d(TAG, "display loading view")
            is CouponDetailUiState.Ready -> updateReadyUiState(state)
        }
    }

    private fun updateReadyUiState(state: CouponDetailUiState.Ready) {
        Log.d(TAG, "display the detail coupon ${state.coupon}")
        ui.banner.setImageResource(state.coupon.image.toDrawableRes())
        configureDiscountBanner(state.coupon.discount)
        configureState(state)
        configureMainTitle(state)
        configureExpirationDate(state)
        configureRelatedProducts(state)
        configureDetails(state)
        configureToolbar(state)
    }

    private fun configureDiscountBanner(discount: Discount) {
        ui.discount.text = "${discount.value}%"
        discount.special?.let {
            ui.discountSpecial.text = it
            ui.discountSpecial.isVisible = true
        }
        val darkColor = ContextCompat.getColor(this, discount.value.toDarkColorRes())
        ui.couponBannerBackground.setBackgroundResource(discount.value.toCouponBannerDrawable())
        ui.discountSpecial.setBackgroundColor(darkColor)
    }

    private fun configureState(state: CouponDetailUiState.Ready) {
        ui.stateButton.setState(state.coupon.state, state.stateCouponClick)
        ui.stateText.setText(state.coupon.state.text())
    }

    private fun configureMainTitle(state: CouponDetailUiState.Ready) {
        ui.title.text = state.coupon.title
        ui.description.text = state.coupon.description
    }

    private fun configureExpirationDate(state: CouponDetailUiState.Ready) {
        ui.expiration.text = getString(
            R.string.expiration_label,
            LocalDate.now().until(state.coupon.expiration).days.toString()
        )
    }

    private fun configureRelatedProducts(state: CouponDetailUiState.Ready) {
        state.coupon.relatedProducts?.let {
            ui.relatedProductsList.adapter = RelatedProductItemAdapter(it)
            ui.relatedProducts.isVisible = true
        }
    }

    private fun configureDetails(state: CouponDetailUiState.Ready) {
        ui.limits.setText(state.coupon.limits.title, state.coupon.limits.description)
        ui.product.setText(getString(R.string.product_code), state.coupon.productCode.toString())
        ui.conditions.setText(getString(R.string.conditions), state.coupon.conditions)
    }

    private fun configureToolbar(state: CouponDetailUiState.Ready) {
        ui.collapsingToolbarLayout.title = state.toolbarTitle
        ui.toolbar.setBackgroundColor(state.toolbarColor)
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