package com.nistra.demy.admins.features.schedules.data.di

import com.nistra.demy.admins.features.schedules.data.repository.ScheduleRepositoryImpl
import com.nistra.demy.admins.features.schedules.domain.repository.ScheduleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface ScheduleRepositoryModule {

    @Binds
    fun provideScheduleRepository(impl: ScheduleRepositoryImpl): ScheduleRepository

}
