package com.nistra.demy.admins.features.courses.data.di

import com.nistra.demy.admins.features.courses.data.remote.api.CoursesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CourseRemoteDataModule {
    @Provides
    @Singleton
    fun provideCourseService(retrofit: Retrofit): CoursesApi {
        return retrofit.create(CoursesApi::class.java)
    }
}
