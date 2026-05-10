package com.nistra.demy.admins.features.periods.data.di

import com.nistra.demy.admins.features.periods.data.remote.api.AcademicPeriodApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AcademicPeriodDataModule {

    @Provides
    @Singleton
    fun providePeriodService(retrofit: Retrofit): AcademicPeriodApi {
        return retrofit.create(AcademicPeriodApi::class.java)
    }
}
