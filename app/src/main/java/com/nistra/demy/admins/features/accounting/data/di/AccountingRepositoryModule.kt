package com.nistra.demy.admins.features.accounting.data.di
import com.nistra.demy.admins.features.accounting.data.repository.TransactionRepositoryImpl
import com.nistra.demy.admins.features.accounting.domain.repository.TransactionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
abstract class AccountingRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindAccountingRepository(
        impl: TransactionRepositoryImpl
    ): TransactionRepository
}
