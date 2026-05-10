package com.nistra.demy.admins.features.students.data.di

import com.nistra.demy.admins.features.students.data.datasource.remote.StudentRemoteDataSource
import com.nistra.demy.admins.features.students.data.datasource.remote.StudentRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
abstract class StudentDataSourceModule {
    @Binds @Singleton
    abstract fun bindStudentRemoteDataSource(impl: StudentRemoteDataSourceImpl): StudentRemoteDataSource
}
