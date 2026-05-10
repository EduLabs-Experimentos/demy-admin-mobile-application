package com.nistra.demy.admins.features.auth.data.di

import com.nistra.demy.admins.features.auth.data.datasource.remote.AuthRemoteDataSource
import com.nistra.demy.admins.features.auth.data.datasource.remote.AuthRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthDataSourceModule {
    @Binds @Singleton
    abstract fun bindAuthRemoteDataSource(impl: AuthRemoteDataSourceImpl): AuthRemoteDataSource
}
