package com.nistra.demy.admins.features.profile.data.di

import com.nistra.demy.admins.features.profile.data.repository.AcademyRepositoryImpl
import com.nistra.demy.admins.features.profile.domain.repository.AcademyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AcademyRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindAcademyRepository(impl: AcademyRepositoryImpl): AcademyRepository
}
