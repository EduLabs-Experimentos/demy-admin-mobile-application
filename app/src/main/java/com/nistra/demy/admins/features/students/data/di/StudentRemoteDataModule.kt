package com.nistra.demy.admins.features.students.data.di

import com.nistra.demy.admins.features.students.data.remote.api.StudentApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object StudentRemoteDataModule {

    @Provides
    @Singleton
    fun provideStudentService(retrofit: Retrofit): StudentApi {
        return retrofit.create(StudentApi::class.java)
    }
}
