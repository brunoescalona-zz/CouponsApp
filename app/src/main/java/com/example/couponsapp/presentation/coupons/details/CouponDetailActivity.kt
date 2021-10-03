package com.example.couponsapp.presentation.coupons.details

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.couponsapp.R

class CouponDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coupon_detail)
    }

    companion object {
        private const val TAG = "CouponDetailActivity"

        private const val COUPON_ID = "coupon_id"

        fun createIntent(fromActivity: Activity, couponId: Long): Intent {
            return Intent(fromActivity, CouponDetailActivity::class.java).apply {
                putExtra(COUPON_ID, couponId)
            }
        }
    }
}