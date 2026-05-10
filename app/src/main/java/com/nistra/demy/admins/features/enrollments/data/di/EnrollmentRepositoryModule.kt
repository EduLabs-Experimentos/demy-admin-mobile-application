package com.nistra.demy.admins.features.enrollments.data.di

import com.nistra.demy.admins.features.enrollments.data.repository.EnrollmentRepositoryImpl
import com.nistra.demy.admins.features.enrollments.domain.repository.EnrollmentRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface EnrollmentRepositoryModule {

    @Binds
    fun bindEnrollmentRepository(
        impl: EnrollmentRepositoryImpl
    ): EnrollmentRepository
}
