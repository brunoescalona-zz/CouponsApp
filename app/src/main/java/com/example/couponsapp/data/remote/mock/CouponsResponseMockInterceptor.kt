package com.example.couponsapp.data.remote.mock

import android.util.Log
import com.example.couponsapp.data.remote.dto.CouponDto
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class CouponsResponseMockInterceptor(private val gson: Gson) : Interceptor {

    private val mockCouponsResponseDto by lazy { CouponsResponseDtoMock.VALUE }

    override fun intercept(chain: Interceptor.Chain): Response {
        val method = chain.request().method
        Log.d(TAG, "request intercepted with method $method")

        val jsonResponse = when (method) {
            REQUEST_METHOD_GET -> gson.toJson(mockCouponsResponseDto)
            REQUEST_METHOD_PUT -> {
                val couponId = getCouponIdFromRequestUrl(chain)
                val coupon = getCoupon(couponId)
                gson.toJson(coupon)
            }
            else -> null
        }

        Log.d(TAG, "mocked json response $jsonResponse")

        return when (jsonResponse) {
            null -> mockFailedResponse(chain)
            else -> mockSuccessfulResponse(chain, jsonResponse)
        }
    }

    private fun getCouponIdFromRequestUrl(chain: Interceptor.Chain): Long {
        val pathSegments = chain.request().url.pathSegments
        return pathSegments[pathSegments.size - 2].toLong()
    }

    private fun getCoupon(id: Long): CouponDto {
        val coupon = mockCouponsResponseDto.coupons
            .find { it.id == id } ?: throw IllegalStateException("")

        return coupon.copy(isEnabled = !coupon.isEnabled)
    }

    private fun mockSuccessfulResponse(
        chain: Interceptor.Chain,
        jsonResponse: String
    ): Response {
        val body = jsonResponse
            .toByteArray()
            .toResponseBody()

        return chain.proceed(chain.request())
            .newBuilder()
            .code(200)
            .protocol(Protocol.HTTP_2)
            .addHeader(HEADER_CONTENT_TYPE, HEADER_CONTENT_TYPE_VALUE)
            .body(body)
            .build()
    }

    private fun mockFailedResponse(chain: Interceptor.Chain) = chain.proceed(chain.request())
        .newBuilder()
        .code(500)
        .protocol(Protocol.HTTP_2)
        .addHeader(HEADER_CONTENT_TYPE, HEADER_CONTENT_TYPE_VALUE)
        .build()

    companion object {
        private const val TAG = "CouponsResponseMock"

        private const val REQUEST_METHOD_GET = "GET"
        private const val REQUEST_METHOD_PUT = "PUT"
        private const val HEADER_CONTENT_TYPE = "content-type"
        private const val HEADER_CONTENT_TYPE_VALUE = "application/json"
    }

}