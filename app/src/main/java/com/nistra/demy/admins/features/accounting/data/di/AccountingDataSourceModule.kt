package com.nistra.demy.admins.features.accounting.data.di
import com.nistra.demy.admins.features.accounting.data.datasource.remote.AccountingRemoteDataSource
import com.nistra.demy.admins.features.accounting.data.datasource.remote.AccountingRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
abstract class AccountingDataSourceModule {
    @Binds
    @Singleton
    abstract fun bindAccountingRemoteDataSource(
        impl: AccountingRemoteDataSourceImpl
    ): AccountingRemoteDataSource
}
