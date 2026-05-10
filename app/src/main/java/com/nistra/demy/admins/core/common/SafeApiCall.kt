package com.nistra.demy.admins.core.common

import retrofit2.HttpException
import java.io.IOException
import android.util.Log

/**
 * Safely executes a suspend API call and provides detailed logging when exceptions occur.
 *
 * This utility centralizes network error handling by interpreting known exceptions
 * such as [IOException] and [HttpException] through [ErrorHandler].
 * Any unexpected exceptions are caught and logged with contextual information
 * for easier debugging.
 *
 * **Usage example:**
 * ```
 * safeApiCall(endpoint = "authentication/sign-in") {
 *     api.signIn(request)
 * }
 * ```
 *
 * When the call fails, this function logs contextual information — including
 * the provided endpoint, the calling class (origin), and the stack trace —
 * before throwing an [Exception] containing a user-friendly message derived from [ErrorHandler].
 *
 * @param T The expected return type of the API call.
 * @param endpoint Optional name or path of the API endpoint (for clearer logs).
 *                 Defaults to `"Unknown endpoint"` if not provided.
 * @param apiCall The suspend lambda representing the network request to execute.
 * @return The successful result of [apiCall] if no exception occurs.
 * @throws Exception When the call fails, containing a parsed user-friendly message.
 * @see ErrorHandler For detailed parsing of HTTP and network exceptions.
 * @author Salim Ramirez
 */
suspend inline fun <reified C, T> C.safeApiCall(
    endpoint: String? = null,
    crossinline apiCall: suspend C.() -> T
): T {
    return try {
        apiCall(this)
    } catch (e: Exception) {
        val origin = this::class.simpleName ?: "UnknownOrigin"
        val userMessage = ErrorHandler.parseException(e)
        val safeEndpoint = endpoint ?: "Unknown endpoint"

        val detailedLog = """
            safeApiCall Exception
            ───────────────────────────────
            • Origin: $origin
            • Endpoint: $safeEndpoint
            • Exception: ${e::class.qualifiedName}
            • RawMessage: ${e.message ?: "No message"}
            • UserMessage: $userMessage
            • StackTrace:
            ${e.stackTraceToString()}
        """.trimIndent()

        Log.e(origin, detailedLog)
        throw Exception(userMessage, e)
    }
}
