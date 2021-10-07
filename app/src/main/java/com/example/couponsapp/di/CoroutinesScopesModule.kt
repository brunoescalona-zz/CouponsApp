package com.example.couponsapp.di

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CoroutinesScopesModule {

    @Singleton
    @Provides
    fun providesCoroutineScope(): CoroutineScope {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            Log.d("CoroutineException", "main exception caught $throwable")
        }
        return CoroutineScope(SupervisorJob() + Dispatchers.Default + exceptionHandler)
    }
}