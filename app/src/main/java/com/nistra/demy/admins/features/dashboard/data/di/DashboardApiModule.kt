package com.nistra.demy.admins.features.dashboard.data.di

import com.nistra.demy.admins.features.dashboard.data.remote.api.DashboardApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Dagger Hilt module for providing dashboard API dependencies.
 *
 * @author Salim Ramirez
 */
@Module
@InstallIn(SingletonComponent::class)
object DashboardApiModule {

    @Provides
    @Singleton
    fun provideDashboardApi(retrofit: Retrofit): DashboardApi {
        return retrofit.create(DashboardApi::class.java)
    }
}

