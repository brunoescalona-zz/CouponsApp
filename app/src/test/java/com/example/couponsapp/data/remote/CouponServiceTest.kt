package com.example.couponsapp.data.remote

import com.example.couponsapp.data.remote.mock.CouponsResponseDtoMock
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Using `runBlocking` because of issue
// https://github.com/Kotlin/kotlinx.coroutines/issues/1204
class CouponServiceTest {

    private lateinit var mockServer: MockWebServer
    private lateinit var couponService: CouponService
    private lateinit var gson: Gson

    @Before
    fun setUp() {
        mockServer = MockWebServer()
        mockServer.start()

        gson = Gson()

        val okHttpClient = OkHttpClient
            .Builder()
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        couponService = retrofit.create(CouponService::class.java)
    }

    @After
    fun tearDown() {
        mockServer.shutdown()
    }

    @Test
    fun `test 200 response for GET coupons`() = runBlocking {
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(gson.toJson(CouponsResponseDtoMock.VALUE))
        mockServer.enqueue(mockResponse)

        val response = couponService.getCoupons()
        val request = mockServer.takeRequest()

        assertThat(response).isEqualTo(CouponsResponseDtoMock.VALUE)
        assertThat(request.path).isEqualTo("/coupons")
        assertThat(request.method).isEqualTo("GET")
    }

    @Test
    fun `test 200 response for PUT coupon`() = runBlocking {
        val firstCoupon = CouponsResponseDtoMock.VALUE.coupons.first()

        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(gson.toJson(firstCoupon.copy(isEnabled = true)))

        mockServer.enqueue(mockResponse)

        val response = couponService.putCouponState(0)
        val request = mockServer.takeRequest()

        assertThat(response).isEqualTo(firstCoupon.copy(isEnabled = true))
        assertThat(request.path).isEqualTo("/coupons/0/state")
        assertThat(request.method).isEqualTo("PUT")
    }
}