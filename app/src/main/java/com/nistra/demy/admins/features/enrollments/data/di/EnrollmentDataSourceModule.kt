package com.nistra.demy.admins.features.enrollments.data.di

import com.nistra.demy.admins.features.enrollments.data.datasource.remote.EnrollmentRemoteDataSource
import com.nistra.demy.admins.features.enrollments.data.datasource.remote.EnrollmentRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class EnrollmentDataSourceModule {
    @Binds
    @Singleton
    abstract fun bindEnrollmentRemoteDataSource(
        impl: EnrollmentRemoteDataSourceImpl
    ): EnrollmentRemoteDataSource
}
