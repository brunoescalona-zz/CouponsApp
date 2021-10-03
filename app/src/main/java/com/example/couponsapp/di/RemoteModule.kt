package com.example.couponsapp.di

import com.example.couponsapp.data.remote.CouponService
import com.example.couponsapp.data.remote.mock.CouponsResponseMockInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun providesGson() = Gson()

    @Provides
    @Singleton
    fun providesMockInterceptor(gson: Gson): Interceptor = CouponsResponseMockInterceptor(gson)

    @Provides
    @Singleton
    fun providesMockInterceptorOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun providesService(okhttpClient: OkHttpClient): CouponService {
        val retrofit = Retrofit.Builder()
            .client(okhttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://example.com/")
            .build()
        return retrofit.create(CouponService::class.java)
    }
}