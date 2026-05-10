package com.nistra.demy.admins.features.teachers.data.di

import com.nistra.demy.admins.features.teachers.data.datasource.remote.TeacherRemoteDataSource
import com.nistra.demy.admins.features.teachers.data.datasource.remote.TeacherRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TeacherDataSourceModule {
    @Binds @Singleton
    abstract fun bindTeachersRemoteDataSource(impl: TeacherRemoteDataSourceImpl): TeacherRemoteDataSource
}
