package com.nistra.demy.admins.features.finance.data.di

import com.nistra.demy.admins.features.finance.data.datasource.remote.FinanceRemoteDataSource
import com.nistra.demy.admins.features.finance.data.datasource.remote.FinanceRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FinanceDataSourceModule {
    @Binds @Singleton
    abstract fun bindFinanceRemoteDataSource(impl: FinanceRemoteDataSourceImpl): FinanceRemoteDataSource
}
