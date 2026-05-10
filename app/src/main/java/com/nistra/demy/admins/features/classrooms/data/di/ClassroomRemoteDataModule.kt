package com.nistra.demy.admins.features.classrooms.data.di

import com.nistra.demy.admins.features.classrooms.data.remote.api.ClassroomsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ClassroomRemoteDataModule {

    @Provides
    @Singleton
    fun provideClassroomService(retrofit: Retrofit): ClassroomsApi {
        return retrofit.create(ClassroomsApi::class.java)
    }
}
