package com.nistra.demy.admins.features.schedules.data.di

import com.nistra.demy.admins.features.schedules.data.remote.api.SchedulesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ScheduleRemoteDataModule {

    @Provides
    @Singleton
    fun provideScheduleService(retrofit: Retrofit): SchedulesApi {
        return retrofit.create(SchedulesApi::class.java)
    }
}
