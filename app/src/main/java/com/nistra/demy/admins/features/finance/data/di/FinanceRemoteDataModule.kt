package com.nistra.demy.admins.features.finance.data.di

import com.nistra.demy.admins.features.finance.data.remote.api.FinanceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FinanceRemoteDataModule {
    @Provides @Singleton
    fun provideFinanceApi(retrofit: Retrofit): FinanceApi {
        return retrofit.create(FinanceApi::class.java)
    }
}
