package com.nistra.demy.admins.features.billing.data.di

import com.nistra.demy.admins.features.billing.data.remote.api.BillingAccountsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BillingAccountRemoteDataModule {

    @Provides @Singleton
    fun provideBillingAccountsApi(retrofit: Retrofit): BillingAccountsApi {
        return retrofit.create(BillingAccountsApi::class.java)
    }
}
