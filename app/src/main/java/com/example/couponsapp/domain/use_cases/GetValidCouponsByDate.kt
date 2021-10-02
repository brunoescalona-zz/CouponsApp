package com.example.couponsapp.domain.use_cases

import com.example.couponsapp.domain.CouponRepository
import com.example.couponsapp.domain.models.Coupon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class GetValidCouponsByDate(
    private val repository: CouponRepository
) {
    operator fun invoke(): Flow<Result<List<Coupon>>> {
        return repository.getAll()
            .map { result ->
                if (result.isFailure) return@map result
                val sortedCoupons = result.getOrNull()
                    ?.filter { it.expiration.isAfter(LocalDate.now()) }
                    ?.sortedBy { it.expiration }
                Result.success(sortedCoupons ?: emptyList())
            }
    }
}