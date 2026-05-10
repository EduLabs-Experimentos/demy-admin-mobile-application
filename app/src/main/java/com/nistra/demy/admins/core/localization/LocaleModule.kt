package com.nistra.demy.admins.core.localization

import com.nistra.demy.admins.core.common.LocaleManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocaleModule {
    @Binds @Singleton
    abstract fun bindLocaleManager(impl: AndroidLocaleManager): LocaleManager
}
