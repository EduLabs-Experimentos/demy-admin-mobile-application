package com.nistra.demy.admins.features.accounting.data.di

import com.nistra.demy.admins.features.accounting.data.remote.api.AccountingApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AccountingRemoteDataModule {
    @Provides
    @Singleton
    fun provideAccountingApi(retrofit: Retrofit): AccountingApi {
        return retrofit.create(AccountingApi::class.java)
    }
}

