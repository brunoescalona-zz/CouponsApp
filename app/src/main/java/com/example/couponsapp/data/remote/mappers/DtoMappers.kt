package com.example.couponsapp.data.remote.mappers

import com.example.couponsapp.data.remote.dto.CouponDto
import com.example.couponsapp.data.remote.dto.RelatedProductDto
import com.example.couponsapp.domain.models.*
import java.time.LocalDate

fun CouponDto.toDomain() = Coupon(
    id = id,
    title = title,
    description = description,
    image = imageUrl,
    state = isEnabled.toState(),
    expiration = LocalDate.parse(date),
    limits = Limits(limits.title, limits.description),
    discount = Discount(discount.value, discount.special),
    relatedProducts = relatedProducts?.map { it.toDomain() },
    productCode = productCode,
    conditions = conditions
)

private fun Boolean.toState() = when (this) {
    true -> State.Enabled
    false -> State.Disabled
}

private fun RelatedProductDto.toDomain() = RelatedProduct(
    id = id,
    name = name,
    icon = iconUrl
)