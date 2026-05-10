package com.nistra.demy.admins.features.students.data.di

import com.nistra.demy.admins.features.students.data.repository.StudentRepositoryImpl
import com.nistra.demy.admins.features.students.domain.repository.StudentRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
interface StudentRepositoryModule {

    @Binds
    abstract fun provideStudentRepository(impl: StudentRepositoryImpl): StudentRepository
}
