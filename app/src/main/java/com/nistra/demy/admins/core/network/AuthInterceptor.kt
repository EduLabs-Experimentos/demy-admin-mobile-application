package com.nistra.demy.admins.core.network

import com.nistra.demy.admins.core.storage.SessionPreferences
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * OkHttp interceptor that automatically adds authentication headers to HTTP requests.
 *
 * This interceptor handles two main responsibilities:
 * 1. Retrieves the authentication token from [SessionPreferences]
 * 2. Adds the token as a Bearer token in the Authorization header if available
 * 3. Always adds "Accept: application/json" header to ensure JSON responses
 *
 * The interceptor is applied to all outgoing HTTP requests through the OkHttpClient.
 * If no token is available (user not logged in), requests proceed without the
 * Authorization header but still include the Accept header.
 *
 * Note: Uses [runBlocking] to retrieve the token synchronously within the interceptor.
 * This is acceptable here as interceptors run on background threads.
 *
 * @property sessionPreferences Repository for accessing stored session/authentication data
 *
 * @author Salim Ramirez
 */
@Singleton
class AuthInterceptor @Inject constructor(
    private val sessionPreferences: SessionPreferences
) : Interceptor {
    /**
     * Intercepts an HTTP request to add authentication and content-type headers.
     *
     * Process flow:
     * 1. Retrieves the current authentication token from session storage
     * 2. If token exists and is not blank:
     *    - Adds "Authorization: Bearer {token}" header
     *    - Adds "Accept: application/json" header
     * 3. If no token exists:
     *    - Only adds "Accept: application/json" header
     * 4. Proceeds with the modified request
     *
     * @param chain The interceptor chain containing the original request
     * @return The HTTP response from the server
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking { sessionPreferences.getToken() }
        val request = if (!token.isNullOrBlank()) {
            chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .addHeader("Accept", "application/json")
                .build()
        } else {
            chain.request().newBuilder()
                .addHeader("Accept", "application/json")
                .build()
        }
        return chain.proceed(request)
    }
}
