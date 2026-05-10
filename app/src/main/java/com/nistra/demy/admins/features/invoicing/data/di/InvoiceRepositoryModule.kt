package com.nistra.demy.admins.features.invoicing.data.di

import com.nistra.demy.admins.features.invoicing.data.repositories.InvoiceRepositoryImpl
import com.nistra.demy.admins.features.invoicing.domain.repository.InvoiceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class InvoiceRepositoryModule {
    @Binds @Singleton
    abstract fun  bindInvoiceRepository(impl: InvoiceRepositoryImpl): InvoiceRepository

}
