package com.nistra.demy.admins.core.network

import com.nistra.demy.admins.BuildConfig
import com.nistra.demy.admins.core.network.adapters.LocalDateAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

/**
 * Dagger Hilt module that provides network-related dependencies.
 *
 * This module configures and provides all necessary components for network communication:
 * - Retrofit instance for API calls
 * - OkHttpClient with authentication and logging interceptors
 * - Moshi for JSON serialization/deserialization
 * - Base URL configuration
 *
 * All provided dependencies are singleton-scoped to ensure a single instance throughout
 * the application lifecycle.
 *
 * @author Salim Ramirez
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Provides the base URL for API endpoints.
     *
     * The URL is retrieved from BuildConfig and can be configured per build variant
     * (debug, release, etc.).
     *
     * @return The API base URL as a String
     */
    @Provides
    @Singleton
    @Named("apiBaseUrl")
    fun provideBaseUrl(): String = BuildConfig.API_BASE_URL

    /**
     * Provides an HTTP logging interceptor for debugging network requests.
     *
     * The logging level is controlled by [BuildConfig.LOG_HTTP]:
     * - When enabled: Logs full request/response bodies (BODY level)
     * - When disabled: No logging (NONE level)
     *
     * This is typically enabled only in debug builds to avoid performance overhead
     * and security concerns in production.
     *
     * @return Configured [HttpLoggingInterceptor] instance
     */
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.LOG_HTTP)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }

    /**
     * Provides a configured OkHttpClient with authentication and logging.
     *
     * The client is configured with two interceptors in order:
     * 1. [AuthInterceptor]: Adds authentication token to requests
     * 2. [HttpLoggingInterceptor]: Logs HTTP traffic for debugging
     *
     * Interceptor order matters - auth headers are added before logging occurs.
     *
     * @param logging The HTTP logging interceptor
     * @param authInterceptor The authentication interceptor
     * @return Configured [OkHttpClient] instance
     */
    @Provides
    @Singleton
    fun provideOkHttp(
        logging: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(logging)
        .build()

    /**
     * Provides a Moshi instance for JSON serialization/deserialization.
     *
     * Configured with [KotlinJsonAdapterFactory] to support Kotlin-specific features
     * like data classes, default parameter values, and nullable types.
     *
     * @return Configured [Moshi] instance
     */
    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(LocalDateAdapter())
        .addLast(KotlinJsonAdapterFactory())
        .build()

    /**
     * Provides the main Retrofit instance for API communication.
     *
     * Retrofit is configured with:
     * - Base URL from BuildConfig
     * - Custom OkHttpClient with auth and logging
     * - Moshi converter for JSON parsing
     *
     * All API service interfaces should be created from this Retrofit instance.
     *
     * @param okHttpClient The configured HTTP client
     * @param moshi The JSON converter
     * @param baseUrl The API base URL
     * @return Configured [Retrofit] instance
     */
    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
        @Named("apiBaseUrl") baseUrl: String
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
}
