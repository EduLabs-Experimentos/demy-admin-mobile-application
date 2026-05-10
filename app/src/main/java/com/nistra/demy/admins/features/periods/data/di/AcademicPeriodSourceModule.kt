package com.nistra.demy.admins.features.periods.data.di

import com.nistra.demy.admins.features.periods.data.datasource.remote.AcademicPeriodRemoteDataSource
import com.nistra.demy.admins.features.periods.data.datasource.remote.AcademicPeriodRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
abstract class AcademicPeriodSourceModule {
    @Binds @Singleton
    abstract fun bindAcademicPeriodRemoteDataSource(impl: AcademicPeriodRemoteDataSourceImpl): AcademicPeriodRemoteDataSource
}
