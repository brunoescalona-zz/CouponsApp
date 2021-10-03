package com.example.couponsapp.data.local.mappers

import com.example.couponsapp.data.local.entities.*
import com.example.couponsapp.domain.models.*
import java.time.LocalDate

fun CouponEntity.toDomain() = Coupon(
    id = id,
    title = title,
    description = description,
    image = imageUrl,
    state = state.toDomain(),
    expiration = LocalDate.parse(date),
    limits = Limits(limits.title, limits.description),
    discount = Discount(discount.value, discount.special),
    relatedProducts = relatedProducts?.map { it.toDomain() },
    productCode = productCode,
    conditions = conditions
)

fun Coupon.toEntity() = CouponEntity(
    id = id,
    title = title,
    description = description,
    imageUrl = image,
    state = state.toEntity(),
    date = expiration.toString(),
    limits = LimitsEntity(limits.title, limits.description),
    discount = DiscountEntity(discount.value, discount.special),
    relatedProducts = relatedProducts?.map { it.toEntity() },
    productCode = productCode,
    conditions = conditions
)

private fun StateEntity.toDomain() = when (this) {
    StateEntity.Disabled -> State.Disabled
    StateEntity.Enabled -> State.Enabled
    StateEntity.Pending -> State.Pending
}

private fun State.toEntity() = when (this) {
    State.Disabled -> StateEntity.Disabled
    State.Enabled -> StateEntity.Enabled
    State.Pending -> StateEntity.Pending
}

private fun RelatedProductEntity.toDomain() = RelatedProduct(
    id = id,
    name = name,
    icon = iconUrl
)

private fun RelatedProduct.toEntity() = RelatedProductEntity(
    id = id,
    name = name,
    iconUrl = icon
)