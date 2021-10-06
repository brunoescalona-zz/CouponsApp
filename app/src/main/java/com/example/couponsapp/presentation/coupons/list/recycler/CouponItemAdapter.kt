package com.example.couponsapp.presentation.coupons.list.recycler

import android.content.res.Resources
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Explode
import androidx.transition.TransitionManager
import com.example.couponsapp.R
import com.example.couponsapp.databinding.ItemCouponBinding
import com.example.couponsapp.domain.models.Coupon
import com.example.couponsapp.domain.models.State
import com.example.couponsapp.presentation.coupons.util.toCouponDrawable
import com.example.couponsapp.presentation.coupons.util.toDarkColorRes
import com.example.couponsapp.presentation.coupons.util.toDrawableRes
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily
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

            // Title and expiration date
            binding.title.text = coupon.title
            binding.expiration.text = context.getString(
                R.string.expiration_label,
                LocalDate.now().until(coupon.expiration).days.toString()
            )
            // Image
            binding.image.setImageResource(coupon.image.toDrawableRes())
            // State button
            binding.stateButton.setState(coupon.state) { itemButtonClick(coupon.id) }
            // Item click
            binding.root.setOnClickListener { itemClick(coupon.id) }

            // Discount coupon configuration
            if (coupon.discount.special == null) {
                binding.discountDescription.text = ""
                binding.discountContainer.setBackgroundColor(Color.TRANSPARENT)
            } else {
                binding.discountContainer.setBackgroundColor(
                    ContextCompat.getColor(context, coupon.discount.value.toDarkColorRes())
                )
                binding.discountDescription.text = coupon.discount.special
            }
            binding.discount.text = "${coupon.discount.value}%"
            binding.couponDiscountValue.setBackgroundResource(
                coupon.discount.value.toCouponDrawable()
            )

            // State check mark
            if (coupon.state == State.Enabled) {
                binding.stateMark.displayAnimated()
            } else {
                binding.stateMark.isVisible = false
            }
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

    private fun ShapeableImageView.displayAnimated() {
        shapeAppearanceModel = shapeAppearanceModel
            .toBuilder()
            .setBottomRightCorner(CornerFamily.ROUNDED, 0f)
            .setTopLeftCorner(CornerFamily.ROUNDED, 0f)
            .setBottomLeftCorner(CornerFamily.ROUNDED, 20.dpToPx().toFloat())
            .build()

        val transition = Explode()
        transition.duration = 400
        TransitionManager.beginDelayedTransition(this.parent as ViewGroup, transition)
        isVisible = true
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemCouponBinding.bind(view)
    }

    private fun Int.dpToPx() = (this * Resources.getSystem().displayMetrics.density).toInt()
}