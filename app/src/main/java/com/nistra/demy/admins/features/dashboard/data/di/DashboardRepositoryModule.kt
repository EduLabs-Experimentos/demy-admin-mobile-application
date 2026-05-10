package com.nistra.demy.admins.features.dashboard.data.di

import com.nistra.demy.admins.features.dashboard.data.datasource.remote.DashboardRemoteDataSource
import com.nistra.demy.admins.features.dashboard.data.datasource.remote.DashboardRemoteDataSourceImpl
import com.nistra.demy.admins.features.dashboard.data.repository.DashboardRepositoryImpl
import com.nistra.demy.admins.features.dashboard.domain.repository.DashboardRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DashboardRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDashboardRepository(
        impl: DashboardRepositoryImpl
    ): DashboardRepository

    @Binds
    @Singleton
    abstract fun bindDashboardRemoteDataSource(
        impl: DashboardRemoteDataSourceImpl
    ): DashboardRemoteDataSource
}
