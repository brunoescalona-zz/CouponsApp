package com.example.couponsapp.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.AndroidJUnit4
import com.example.couponsapp.data.local.mock.CouponEntitiesMock
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class CouponAppDatabaseTest {

    private lateinit var couponDao: CouponDao
    private lateinit var db: CouponAppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room
            .inMemoryDatabaseBuilder(
                context,
                CouponAppDatabase::class.java
            )
            .allowMainThreadQueries()
            .build()
        couponDao = db.couponDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun test_insert_all_and_get_all() = runBlocking {
        val couponEntities = CouponEntitiesMock.VALUE.toTypedArray()
        couponDao.insertAll(*couponEntities)

        couponDao
            .getAll()
            .take(1)
            .collect { list ->
                assertThat(list).isEqualTo(CouponEntitiesMock.VALUE)
            }
    }

    @Test
    @Throws(Exception::class)
    fun test_enable_coupon_and_get_all() = runBlocking {
        val couponEntities = CouponEntitiesMock.VALUE.toTypedArray()
        couponDao.insertAll(*couponEntities)
        val couponEnabled = CouponEntitiesMock.VALUE.last().copy(isEnabled = true)
        couponDao.insert(couponEnabled)

        val expectedEntities = CouponEntitiesMock.VALUE
            .dropLast(1)
            .plus(couponEnabled)

        couponDao
            .getAll()
            .take(1)
            .collect { list ->
                assertThat(list).isEqualTo(expectedEntities)
            }
    }
}