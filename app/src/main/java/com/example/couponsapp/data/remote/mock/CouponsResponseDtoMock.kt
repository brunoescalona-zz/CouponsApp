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
                imageUrl = "https://content.fake.com/image0",
                isEnabled = false,
                date = LocalDate.now().plusDays(5).toString(),
                limits = LimitsDto(title = "Limited to fruits", description = "Only one usage"),
                discount = DiscountDto(value = 25, special = null),
                relatedProducts = listOf(
                    RelatedProductDto(
                        id = 0,
                        name = "Strawberries",
                        iconUrl = "https://content.fake.com/icon0"
                    ),
                    RelatedProductDto(
                        id = 1,
                        name = "Oranges",
                        iconUrl = "https://content.fake.com/icon1"
                    ),
                    RelatedProductDto(
                        id = 2,
                        name = "Bananas",
                        iconUrl = "https://content.fake.com/icon2"
                    ),
                    RelatedProductDto(
                        id = 3,
                        name = "Watermelons",
                        iconUrl = "https://content.fake.com/icon3"
                    ),
                    RelatedProductDto(
                        id = 4,
                        name = "Apples",
                        iconUrl = "https://content.fake.com/icon4"
                    )
                ),
                productCode = 11111111L,
                conditions = "Valid only until the end of the month. Valid only in Spain"
            ),
            CouponDto(
                id = 1L,
                title = "All kind of potatoes",
                description = "Discover all the varieties in your store!",
                imageUrl = "https://content.fake.com/image1",
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
                imageUrl = "https://content.fake.com/image2",
                isEnabled = false,
                date = LocalDate.now().plusDays(8).toString(),
                limits = LimitsDto(title = "Limited to vegetables", description = "Only one usage"),
                discount = DiscountDto(value = 20, special = null),
                relatedProducts = listOf(
                    RelatedProductDto(
                        id = 0,
                        name = "Strawberries",
                        iconUrl = "https://content.fake.com/icon0"
                    ),
                    RelatedProductDto(
                        id = 1,
                        name = "Oranges",
                        iconUrl = "https://content.fake.com/icon1"
                    ),
                    RelatedProductDto(
                        id = 2,
                        name = "Bananas",
                        iconUrl = "https://content.fake.com/icon2"
                    ),
                    RelatedProductDto(
                        id = 3,
                        name = "Watermelons",
                        iconUrl = "https://content.fake.com/icon3"
                    ),
                    RelatedProductDto(
                        id = 4,
                        name = "Apples",
                        iconUrl = "https://content.fake.com/icon4"
                    )
                ),
                productCode = 33333333L,
                conditions = "Valid only until the end of the month. Valid only in Spain"
            ),
            CouponDto(
                id = 3L,
                title = "Bread and pastries",
                description = "Discount applicable in the bakery for freshly made products",
                imageUrl = "https://content.fake.com/image3",
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
                imageUrl = "https://content.fake.com/image4",
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