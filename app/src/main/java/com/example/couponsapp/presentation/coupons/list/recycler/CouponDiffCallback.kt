package com.example.couponsapp.presentation.coupons.list.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.couponsapp.domain.models.Coupon

class CouponDiffCallback(
    private val newCoupons: List<Coupon>,
    private val oldCoupons: List<Coupon>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldCoupons.size

    override fun getNewListSize() = newCoupons.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldCoupons[oldItemPosition].id == newCoupons[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldCoupons[oldItemPosition] == newCoupons[newItemPosition]
    }
}