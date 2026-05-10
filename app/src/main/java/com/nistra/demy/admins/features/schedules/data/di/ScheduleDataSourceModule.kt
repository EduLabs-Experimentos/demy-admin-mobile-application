package com.nistra.demy.admins.features.schedules.data.di

import com.nistra.demy.admins.features.schedules.data.datasource.remote.ScheduleRemoteDataSource
import com.nistra.demy.admins.features.schedules.data.datasource.remote.ScheduleRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ScheduleDataSourceModule {
    @Binds @Singleton
    abstract fun bindScheduleRemoteDataSource(impl: ScheduleRemoteDataSourceImpl): ScheduleRemoteDataSource
}
