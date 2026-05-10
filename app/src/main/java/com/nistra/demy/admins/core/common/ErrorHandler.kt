package com.nistra.demy.admins.core.common

import com.squareup.moshi.JsonDataException
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException

/**
 * Interprets network and HTTP exceptions into user-friendly messages.
 *
 * If the backend returns a structured ErrorResource, it extracts its "message".
 *
 * Expected backend format (ErrorResource):
 * ```
 * {
 *   "timestamp": "...",
 *   "status": 400,
 *   "error": "Bad Request",
 *   "code": "DOMAIN_TENANT_ASSIGNMENT",
 *   "message": "Tenant ID cannot be null for sign-up event",
 *   "path": "/api/v1/authentication/sign-up"
 * }
 * ```
 *
 * @throws IOException for network-related issues.
 * @throws HttpException for HTTP protocol errors.
 * @author Salim Ramirez
 */
object ErrorHandler {

    /**
     * Converts an exception into a readable and friendly message.
     */
    fun parseException(exception: Throwable): String {
        return when (exception) {
            is IOException -> "Network error. Please check your internet connection."
            is JsonDataException -> "Data parsing error. Please try again later."
            is HttpException -> parseHttpException(exception)
            else -> "An unexpected error occurred."
        }
    }

    /**
     * Analyzes HTTP errors (e.g., 400–500) and extracts message from backend if available.
     */
    private fun parseHttpException(exception: HttpException): String {
        val code = exception.code()
        val errorBody = exception.response()?.errorBody()

        // Try to parse ErrorResource JSON
        val apiMessage = parseErrorResource(errorBody)?.message
        if (!apiMessage.isNullOrBlank()) return apiMessage

        // Fallback to generic messages by HTTP code
        return when (code) {
            400 -> "Bad request. Please check your input."
            401 -> "Unauthorized access. Please check your credentials."
            403 -> "Forbidden access. You don't have permission to access this resource."
            404 -> "Resource not found."
            409 -> "Conflict error. The resource already exists."
            in 500..509 -> "Server error. Please try again later."
            else -> "Unknown server error occurred ($code)."
        }
    }

    /**
     * Attempts to extract an ErrorResource from the backend error body.
     */
    private fun parseErrorResource(errorBody: ResponseBody?): ErrorResource? {
        return try {
            errorBody?.string()?.let {
                val json = JSONObject(it)
                ErrorResource(
                    timestamp = json.optString("timestamp"),
                    status = json.optInt("status"),
                    error = json.optString("error"),
                    code = json.optString("code"),
                    message = json.optString("message"),
                    path = json.optString("path")
                )
            }
        } catch (_: Exception) {
            null
        }
    }

    /**
     * Represents the structured error response from the backend.
     */
    private data class ErrorResource(
        val timestamp: String?,
        val status: Int?,
        val error: String?,
        val code: String?,
        val message: String?,
        val path: String?
    )
}
