package com.nistra.demy.admins.features.billing.data.di

import com.nistra.demy.admins.features.billing.data.repositories.BillingAccountRepositoryImpl
import com.nistra.demy.admins.features.billing.domain.repository.BillingAccountRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BillingAccountRepositoryModule {
    @Binds @Singleton
    abstract fun  bindBillingAccountRepository(impl: BillingAccountRepositoryImpl): BillingAccountRepository
}
