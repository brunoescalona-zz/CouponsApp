package com.example.couponsapp.presentation.coupons.list

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.couponsapp.R
import com.example.couponsapp.databinding.ActivityCouponListBinding
import com.example.couponsapp.presentation.coupons.details.CouponDetailActivity
import com.example.couponsapp.presentation.coupons.list.recycler.CouponItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CouponListActivity : AppCompatActivity() {

    private val viewModel: CouponListViewModel by viewModels()

    private lateinit var ui: ActivityCouponListBinding
    private val adapter by lazy { CouponItemAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityCouponListBinding.inflate(layoutInflater)
        setContentView(ui.root)
        setSupportActionBar(ui.toolbar)

        ui.listContainer.adapter = adapter

        viewModel.uiState.observe(this) { uiState ->
            when (uiState) {
                CouponListUiState.Empty -> Log.d(TAG, "display empty view")
                CouponListUiState.Error -> Log.d(TAG, "display error view")
                CouponListUiState.Loading -> Log.d(TAG, "display loading view")
                is CouponListUiState.Ready -> updateReadyUiState(uiState)
            }
        }

        lifecycleScope.launch {
            viewModel.uiAction.collect { uiAction ->
                Log.d(TAG, "uiAction received $uiAction")
                when (uiAction) {
                    is CouponListUiAction.NavigateToDetail -> navigateToDetailView(uiAction.couponId)
                }
            }
        }
    }

    private fun updateReadyUiState(state: CouponListUiState.Ready) {
        Log.d(TAG, "display ready view with ${state.coupons}")

        adapter.updateData(
            state.coupons,
            state.couponClick,
            state.stateCouponClick
        )

        ui.collapsingToolbarLayout.title = resources.getString(
            R.string.app_title,
            state.activeCoupons.toString()
        )
    }

    private fun navigateToDetailView(couponId: Long) {
        val intent = CouponDetailActivity.createIntent(this, couponId)
        startActivity(intent)
    }

    companion object {
        private const val TAG = "CouponListActivity"
    }
}