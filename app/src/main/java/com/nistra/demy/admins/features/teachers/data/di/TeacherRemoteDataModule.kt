package com.nistra.demy.admins.features.teachers.data.di

import com.nistra.demy.admins.features.teachers.data.remote.api.TeachersApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TeacherRemoteDataModule {
    @Provides @Singleton
    fun provideTeachersApi(retrofit: Retrofit): TeachersApi {
        return retrofit.create(TeachersApi::class.java)
    }
}
