package com.nistra.demy.admins.features.periods.data.di

import com.nistra.demy.admins.features.periods.data.repository.AcademicPeriodRepositoryImpl
import com.nistra.demy.admins.features.periods.domain.repository.AcademicPeriodRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface AcademicPeriodRepositoryModule {

    @Binds
    abstract fun provideAcademicPeriodRepository(impl: AcademicPeriodRepositoryImpl): AcademicPeriodRepository
}
