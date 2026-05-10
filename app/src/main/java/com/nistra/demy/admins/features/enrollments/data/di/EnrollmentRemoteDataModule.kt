package com.nistra.demy.admins.features.enrollments.data.di

import com.nistra.demy.admins.features.enrollments.data.remote.api.EnrollmentApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EnrollmentRemoteDataModule {

    @Provides
    @Singleton
    fun provideEnrollmentService(retrofit: Retrofit): EnrollmentApi {
        return retrofit.create(EnrollmentApi::class.java)
    }
}
