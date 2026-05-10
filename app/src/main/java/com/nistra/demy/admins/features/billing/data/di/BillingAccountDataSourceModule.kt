package com.nistra.demy.admins.features.billing.data.di

import com.nistra.demy.admins.features.billing.data.datasource.remote.BillingAccountRemoteDataSource
import com.nistra.demy.admins.features.billing.data.datasource.remote.BillingAccountRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
 abstract class BillingAccountDataSourceModule {
     @Binds @Singleton
     abstract  fun bindBillingAccountsRemoteDataSource(impl: BillingAccountRemoteDataSourceImpl): BillingAccountRemoteDataSource

}
