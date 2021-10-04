package com.example.couponsapp.presentation.coupons.details.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.couponsapp.R
import com.example.couponsapp.databinding.ItemRelatedProductBinding
import com.example.couponsapp.domain.models.RelatedProduct
import com.example.couponsapp.presentation.coupons.util.toDrawableRes

class RelatedProductItemAdapter(
    val relatedProducts: List<RelatedProduct>
) : RecyclerView.Adapter<RelatedProductItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_related_product, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val relatedProduct = relatedProducts[position]
        with(viewHolder) {
            binding.text.text = relatedProduct.name
            binding.icon.setImageResource(relatedProduct.icon.toDrawableRes())
        }
    }

    override fun getItemCount() = relatedProducts.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemRelatedProductBinding.bind(view)
    }
}