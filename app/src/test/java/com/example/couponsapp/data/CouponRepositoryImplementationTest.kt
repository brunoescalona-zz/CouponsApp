package com.example.couponsapp.data

import com.example.couponsapp.data.local.CouponDao
import com.example.couponsapp.data.local.FakeCouponDaoImplementation
import com.example.couponsapp.data.local.mock.CouponEntitiesTestMock
import com.example.couponsapp.data.remote.CouponService
import com.example.couponsapp.data.remote.FakeCouponServiceImplementation
import com.example.couponsapp.domain.CouponRepository
import com.example.couponsapp.domain.mock.CouponsMock
import com.example.couponsapp.domain.models.Coupon
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CouponRepositoryImplementationTest {

    private lateinit var couponDao: CouponDao
    private lateinit var couponService: CouponService
    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher + Job())

    private lateinit var couponRepository: CouponRepository

    @Before
    fun setUp() {
        couponDao = FakeCouponDaoImplementation()
        couponService = FakeCouponServiceImplementation()
        couponRepository = CouponRepositoryImplementation(
            couponService = couponService,
            couponDao = couponDao,
            externalScope = testScope
        )
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        testScope.cancel()
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when getting all with no data stored locally it will return an empty list`() =
        runBlocking {
            couponRepository.getAll()
                .take(1)
                .collect {
                    assertThat(it).isEqualTo(Result.success(emptyList<Coupon>()))
                }
        }

    @Test
    fun `when getting all with data stored locally it will return a list`() = runBlocking {
        couponDao.insertAll(*CouponEntitiesTestMock.list.toTypedArray())

        couponRepository.getAll()
            .take(1)
            .collect {
                assertThat(it).isEqualTo(Result.success(CouponsMock.list))
            }
    }

    @Test
    fun `when request coupon by id it will return the coupon`() = runBlocking {
        couponDao.insertAll(*CouponEntitiesTestMock.list.toTypedArray())

        couponRepository.get(0)
            .take(1)
            .collect {
                assertThat(it).isEqualTo(CouponsMock.coupon0)
            }
    }

    @Test
    fun `when request coupon by id and not exists it will return the null`() = runBlocking {
        couponDao.insertAll(*CouponEntitiesTestMock.list.toTypedArray())

        couponRepository.get(6)
            .take(1)
            .collect {
                assertThat(it).isNull()
            }
    }

}