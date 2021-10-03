package com.example.couponsapp.data.local.mock

import com.example.couponsapp.data.local.entities.*
import java.time.LocalDate

object CouponEntitiesTestMock {

    val coupon0 = CouponEntity(
        id = 0L,
        title = "Fresh fruits",
        description = "Enjoy the discount in our fruit store for all the fresh fruit." +
                " Vegetables are not included. The maximum discount is 6€",
        imageUrl = "https://content.fake.com/image.png",
        state = StateEntity.Disabled,
        date = LocalDate.now().plusDays(5).toString(),
        limits = LimitsEntity(title = "Limited to fruits", description = "Only one usage"),
        discount = DiscountEntity(value = 25, special = null),
        relatedProducts = listOf(
            RelatedProductEntity(
                id = 0,
                name = "Strawberries",
                iconUrl = "https://content.fake.com/image.png"
            ),
            RelatedProductEntity(
                id = 1,
                name = "Oranges",
                iconUrl = "https://content.fake.com/image.png"
            ),
            RelatedProductEntity(
                id = 2,
                name = "Bananas",
                iconUrl = "https://content.fake.com/image.png"
            ),
            RelatedProductEntity(
                id = 3,
                name = "Watermelons",
                iconUrl = "https://content.fake.com/image.png"
            ),
            RelatedProductEntity(
                id = 4,
                name = "Apples",
                iconUrl = "https://content.fake.com/image.png"
            )
        ),
        productCode = 11111111L,
        conditions = "Valid only until the end of the month. Valid only in Spain"
    )
    private val coupon1 = CouponEntity(
        id = 1L,
        title = "All kind of potatoes",
        description = "Discover all the varieties in your store!",
        imageUrl = "https://content.fake.com/image.png",
        state = StateEntity.Disabled,
        date = LocalDate.now().minusDays(3).toString(),
        limits = LimitsEntity(title = "Limited to 4kg", description = "Only one usage"),
        discount = DiscountEntity(value = 50, special = "Special coupon"),
        relatedProducts = null,
        productCode = 22222222L,
        conditions = "Valid only until the end of the month. Valid only in Spain"
    )
    private val coupon2 = CouponEntity(
        id = 2L,
        title = "Fresh vegetables",
        description = "Enjoy the discount in our vegetables and stay healthy",
        imageUrl = "https://content.fake.com/image.png",
        state = StateEntity.Disabled,
        date = LocalDate.now().plusDays(8).toString(),
        limits = LimitsEntity(title = "Limited to vegetables", description = "Only one usage"),
        discount = DiscountEntity(value = 20, special = null),
        relatedProducts = listOf(
            RelatedProductEntity(
                id = 0,
                name = "Strawberries",
                iconUrl = "https://content.fake.com/image.png"
            ),
            RelatedProductEntity(
                id = 1,
                name = "Oranges",
                iconUrl = "https://content.fake.com/image.png"
            ),
            RelatedProductEntity(
                id = 2,
                name = "Bananas",
                iconUrl = "https://content.fake.com/image.png"
            ),
            RelatedProductEntity(
                id = 3,
                name = "Watermelons",
                iconUrl = "https://content.fake.com/image.png"
            ),
            RelatedProductEntity(
                id = 4,
                name = "Apples",
                iconUrl = "https://content.fake.com/image.png"
            )
        ),
        productCode = 33333333L,
        conditions = "Valid only until the end of the month. Valid only in Spain"
    )
    private val coupon3 = CouponEntity(
        id = 3L,
        title = "Bread and pastries",
        description = "Discount applicable in the bakery for freshly made products",
        imageUrl = "https://content.fake.com/image.png",
        state = StateEntity.Disabled,
        date = LocalDate.now().plusDays(6).toString(),
        limits = LimitsEntity(title = "Bakery section", description = "Only one usage"),
        discount = DiscountEntity(value = 20, special = null),
        relatedProducts = null,
        productCode = 44444444L,
        conditions = "Valid only until the end of the month. Valid only in Spain"
    )

    private val coupon4 = CouponEntity(
        id = 4L,
        title = "Pizzas from Italy",
        description = "Enjoy amazing pizzas taken directly from Italy",
        imageUrl = "https://content.fake.com/image.png",
        state = StateEntity.Disabled,
        date = LocalDate.now().plusDays(1).toString(),
        limits = LimitsEntity(title = "Limited to 6 units", description = "Only one usage"),
        discount = DiscountEntity(value = 20, special = null),
        relatedProducts = null,
        productCode = 55555555L,
        conditions = "Valid only until the end of the month. Valid only in Spain"
    )
}