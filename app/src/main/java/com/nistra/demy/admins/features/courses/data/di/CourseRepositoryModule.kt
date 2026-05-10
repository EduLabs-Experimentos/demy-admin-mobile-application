package com.nistra.demy.admins.features.courses.data.di

import com.nistra.demy.admins.features.courses.data.repository.CourseRepositoryImpl
import com.nistra.demy.admins.features.courses.domain.repository.CourseRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface CourseRepositoryModule {

    @Binds
    abstract fun provideCourseRepository(impl: CourseRepositoryImpl): CourseRepository

}
