package com.nistra.demy.admins.features.teachers.data.di

import com.nistra.demy.admins.features.teachers.data.repository.TeacherRepositoryImpl
import com.nistra.demy.admins.features.teachers.domain.repository.TeacherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TeacherRepositoryModule {
    @Binds @Singleton
    abstract fun bindTeacherRepository(impl: TeacherRepositoryImpl): TeacherRepository
}
