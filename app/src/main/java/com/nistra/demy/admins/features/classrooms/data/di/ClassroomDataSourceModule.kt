package com.nistra.demy.admins.features.classrooms.data.di

import com.nistra.demy.admins.features.classrooms.data.datasource.remote.ClassroomRemoteDataSource
import com.nistra.demy.admins.features.classrooms.data.datasource.remote.ClassroomRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ClassroomDataSourceModule {
    @Binds @Singleton
    abstract fun bindClassroomRemoteDataSource(impl: ClassroomRemoteDataSourceImpl): ClassroomRemoteDataSource
}
