package com.nistra.demy.admins.features.classrooms.data.di

import com.nistra.demy.admins.features.classrooms.data.repository.ClassroomRepositoryImpl
import com.nistra.demy.admins.features.classrooms.domain.repository.ClassroomRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface ClassroomRepositoryModule {

    @Binds
    abstract fun provideClassroomRepository(impl: ClassroomRepositoryImpl): ClassroomRepository


}
