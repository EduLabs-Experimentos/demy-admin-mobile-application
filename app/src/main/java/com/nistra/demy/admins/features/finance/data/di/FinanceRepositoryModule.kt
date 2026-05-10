package com.nistra.demy.admins.features.finance.data.di

import com.nistra.demy.admins.features.finance.data.repository.TransactionRepositoryImpl
import com.nistra.demy.admins.features.finance.domain.repository.TransactionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FinanceRepositoryModule {
    @Binds @Singleton
    abstract fun bindFinanceRepository(impl: TransactionRepositoryImpl): TransactionRepository
}
