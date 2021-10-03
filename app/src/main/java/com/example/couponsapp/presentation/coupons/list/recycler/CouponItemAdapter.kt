package com.example.couponsapp.presentation.coupons.list.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.couponsapp.R
import com.example.couponsapp.databinding.ItemCouponBinding
import com.example.couponsapp.domain.models.Coupon
import com.example.couponsapp.domain.models.State
import com.example.couponsapp.presentation.coupons.util.toColorRes
import com.example.couponsapp.presentation.coupons.util.toDarkColorRes
import com.example.couponsapp.presentation.coupons.util.toDrawableRes
import com.google.android.material.button.MaterialButton
import java.time.LocalDate

class CouponItemAdapter(
    private var coupons: List<Coupon> = emptyList(),
    private var itemClick: (Long) -> Unit = {},
    private var itemButtonClick: (Long) -> Unit = {}
) : RecyclerView.Adapter<CouponItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_coupon, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val coupon = coupons[position]
        with(viewHolder) {

            val context = binding.root.context

            binding.title.text = coupon.title
            binding.image.setImageResource(coupon.image.toDrawableRes())
            binding.button.styleButtonForState(coupon.state)
            binding.expiration.text = context.getString(
                R.string.expiration_label,
                LocalDate.now().until(coupon.expiration).days.toString()
            )
            binding.root.setOnClickListener { itemClick(coupon.id) }
            binding.button.setOnClickListener { itemButtonClick(coupon.id) }

            binding.couponDiscountDetails.isInvisible = coupon.discount.special == null
            binding.discountDescription.text = coupon.discount.special
            binding.couponDiscountDetails.setBackgroundColor(
                ContextCompat.getColor(context, coupon.discount.value.toDarkColorRes())
            )
            binding.discount.text = "${coupon.discount.value}%"
            binding.couponDiscountValue.setBackgroundColor(
                ContextCompat.getColor(context, coupon.discount.value.toColorRes())
            )
        }
    }

    override fun getItemCount() = coupons.size

    fun updateData(
        coupons: List<Coupon>,
        itemClick: (Long) -> Unit,
        itemButtonClick: (Long) -> Unit
    ) {
        DiffUtil
            .calculateDiff(CouponDiffCallback(coupons, this.coupons))
            .dispatchUpdatesTo(this)
        this.coupons = coupons
        this.itemClick = itemClick
        this.itemButtonClick = itemButtonClick
    }

    private fun MaterialButton.styleButtonForState(couponState: State) {
        val buttonText = when (couponState) {
            State.Disabled -> R.string.button_enable
            State.Enabled -> R.string.button_enabled
            State.Pending -> R.string.button_loading
        }
        setText(buttonText)

        isEnabled = couponState != State.Pending
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemCouponBinding.bind(view)
    }

}