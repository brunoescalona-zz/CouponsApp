package com.example.couponsapp.domain.mock

import com.example.couponsapp.domain.models.*
import java.time.LocalDate

object CouponsMock {

    private val coupon0 = Coupon(
        id = 0L,
        title = "Fresh fruits",
        description = "Enjoy the discount in our fruit store for all the fresh fruit." +
                " Vegetables are not included. The maximum discount is 6€",
        image = 1,
        state = State.Disabled,
        expiration = LocalDate.now().plusDays(5),
        limits = Limits(title = "Limited to fruits", description = "Only one usage"),
        discount = Discount(value = 25, special = null),
        relatedProducts = listOf(
            RelatedProduct(
                id = 0,
                name = "Strawberries",
                icon = 1
            ),
            RelatedProduct(
                id = 1,
                name = "Oranges",
                icon = 1
            ),
            RelatedProduct(
                id = 2,
                name = "Bananas",
                icon = 1
            ),
            RelatedProduct(
                id = 3,
                name = "Watermelons",
                icon = 1
            ),
            RelatedProduct(
                id = 4,
                name = "Apples",
                icon = 1
            )
        ),
        productCode = 11111111L,
        conditions = "Valid only until the end of the month. Valid only in Spain"
    )
    private val coupon1 = Coupon(
        id = 1L,
        title = "All kind of potatoes",
        description = "Discover all the varieties in your store!",
        image = 1,
        state = State.Disabled,
        expiration = LocalDate.now().minusDays(3),
        limits = Limits(title = "Limited to 4kg", description = "Only one usage"),
        discount = Discount(value = 50, special = "Special coupon"),
        relatedProducts = null,
        productCode = 22222222L,
        conditions = "Valid only until the end of the month. Valid only in Spain"
    )
    private val coupon2 = Coupon(
        id = 2L,
        title = "Fresh vegetables",
        description = "Enjoy the discount in our vegetables and stay healthy",
        image = 1,
        state = State.Disabled,
        expiration = LocalDate.now().plusDays(8),
        limits = Limits(title = "Limited to vegetables", description = "Only one usage"),
        discount = Discount(value = 20, special = null),
        relatedProducts = listOf(
            RelatedProduct(
                id = 0,
                name = "Strawberries",
                icon = 1
            ),
            RelatedProduct(
                id = 1,
                name = "Oranges",
                icon = 1
            ),
            RelatedProduct(
                id = 2,
                name = "Bananas",
                icon = 1
            ),
            RelatedProduct(
                id = 3,
                name = "Watermelons",
                icon = 1
            ),
            RelatedProduct(
                id = 4,
                name = "Apples",
                icon = 1
            )
        ),
        productCode = 33333333L,
        conditions = "Valid only until the end of the month. Valid only in Spain"
    )
    val coupon3 = Coupon(
        id = 3L,
        title = "Bread and pastries",
        description = "Discount applicable in the bakery for freshly made products",
        image = 1,
        state = State.Disabled,
        expiration = LocalDate.now().plusDays(6),
        limits = Limits(title = "Bakery section", description = "Only one usage"),
        discount = Discount(value = 20, special = null),
        relatedProducts = null,
        productCode = 44444444L,
        conditions = "Valid only until the end of the month. Valid only in Spain"
    )

    private val coupon4 = Coupon(
        id = 4L,
        title = "Pizzas from Italy",
        description = "Enjoy amazing pizzas taken directly from Italy",
        image = 1,
        state = State.Disabled,
        expiration = LocalDate.now().plusDays(1),
        limits = Limits(title = "Limited to 6 units", description = "Only one usage"),
        discount = Discount(value = 20, special = null),
        relatedProducts = null,
        productCode = 55555555L,
        conditions = "Valid only until the end of the month. Valid only in Spain"
    )
    val list = listOf(
        coupon0,
        coupon1,
        coupon2,
        coupon3,
        coupon4
    )

    val ordered = listOf(
        coupon4,
        coupon0,
        coupon3,
        coupon2
    )
}