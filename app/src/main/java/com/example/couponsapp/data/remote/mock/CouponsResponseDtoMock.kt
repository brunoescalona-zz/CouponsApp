package com.example.couponsapp.data.remote.mock

import com.example.couponsapp.data.remote.dto.*
import java.time.LocalDate

object CouponsResponseDtoMock {

    val VALUE = CouponsResponseDto(
        coupons = listOf(
            CouponDto(
                id = 0L,
                title = "Fresh fruits",
                description = "Enjoy the discount in our fruit store for all the fresh fruit." +
                        " Vegetables are not included. The maximum discount is 6€",
                imageUrl = "https://content.fake.com/image.png",
                isEnabled = false,
                date = LocalDate.now().plusDays(5).toString(),
                limits = LimitsDto(title = "Limited to fruits", description = "Only one usage"),
                discount = DiscountDto(value = 25, special = null),
                relatedProducts = listOf(
                    RelatedProductDto(
                        name = "Strawberries",
                        iconUrl = "https://content.fake.com/image.png"
                    ),
                    RelatedProductDto(
                        name = "Oranges",
                        iconUrl = "https://content.fake.com/image.png"
                    ),
                    RelatedProductDto(
                        name = "Bananas",
                        iconUrl = "https://content.fake.com/image.png"
                    ),
                    RelatedProductDto(
                        name = "Watermelons",
                        iconUrl = "https://content.fake.com/image.png"
                    ),
                    RelatedProductDto(
                        name = "Apples",
                        iconUrl = "https://content.fake.com/image.png"
                    )
                ),
                productCode = 11111111L,
                conditions = "Valid only until the end of the month. Valid only in Spain"
            ),
            CouponDto(
                id = 1L,
                title = "All kind of potatoes",
                description = "Discover all the varieties in your store!",
                imageUrl = "https://content.fake.com/image.png",
                isEnabled = false,
                date = LocalDate.now().plusDays(3).toString(),
                limits = LimitsDto(title = "Limited to 4kg", description = "Only one usage"),
                discount = DiscountDto(value = 50, special = "Special coupon"),
                relatedProducts = null,
                productCode = 22222222L,
                conditions = "Valid only until the end of the month. Valid only in Spain"
            ),
            CouponDto(
                id = 2L,
                title = "Fresh vegetables",
                description = "Enjoy the discount in our vegetables and stay healthy",
                imageUrl = "https://content.fake.com/image.png",
                isEnabled = false,
                date = LocalDate.now().plusDays(8).toString(),
                limits = LimitsDto(title = "Limited to vegetables", description = "Only one usage"),
                discount = DiscountDto(value = 20, special = null),
                relatedProducts = listOf(
                    RelatedProductDto(
                        name = "Strawberries",
                        iconUrl = "https://content.fake.com/image.png"
                    ),
                    RelatedProductDto(
                        name = "Oranges",
                        iconUrl = "https://content.fake.com/image.png"
                    ),
                    RelatedProductDto(
                        name = "Bananas",
                        iconUrl = "https://content.fake.com/image.png"
                    ),
                    RelatedProductDto(
                        name = "Watermelons",
                        iconUrl = "https://content.fake.com/image.png"
                    ),
                    RelatedProductDto(
                        name = "Apples",
                        iconUrl = "https://content.fake.com/image.png"
                    )
                ),
                productCode = 33333333L,
                conditions = "Valid only until the end of the month. Valid only in Spain"
            ),
            CouponDto(
                id = 3L,
                title = "Bread and pastries",
                description = "Discount applicable in the bakery for freshly made products",
                imageUrl = "https://content.fake.com/image.png",
                isEnabled = false,
                date = LocalDate.now().plusDays(6).toString(),
                limits = LimitsDto(title = "Bakery section", description = "Only one usage"),
                discount = DiscountDto(value = 20, special = null),
                relatedProducts = null,
                productCode = 44444444L,
                conditions = "Valid only until the end of the month. Valid only in Spain"
            ),
            CouponDto(
                id = 4L,
                title = "Pizzas from Italy",
                description = "Enjoy amazing pizzas taken directly from Italy",
                imageUrl = "https://content.fake.com/image.png",
                isEnabled = false,
                date = LocalDate.now().plusDays(1).toString(),
                limits = LimitsDto(title = "Limited to 6 units", description = "Only one usage"),
                discount = DiscountDto(value = 20, special = null),
                relatedProducts = null,
                productCode = 55555555L,
                conditions = "Valid only until the end of the month. Valid only in Spain"
            )
        )
    )
}