package com.nistra.demy.admins.features.courses.data.di

import com.nistra.demy.admins.features.courses.data.datasource.remote.CourseRemoteDataSource
import com.nistra.demy.admins.features.courses.data.datasource.remote.CourseRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CourseDataSourceModule {
    @Binds @Singleton
    abstract fun bindCourseRemoteDataSource(impl: CourseRemoteDataSourceImpl): CourseRemoteDataSource
}
