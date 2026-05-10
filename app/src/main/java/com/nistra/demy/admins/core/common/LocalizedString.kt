package com.nistra.demy.admins.core.common

import androidx.annotation.StringRes

/**
 * Represents a localizable string resource.
 *
 * This sealed class provides type-safe access to string resources,
 * avoiding the use of reflection (getIdentifier) which is discouraged
 * for performance and build optimization reasons.
 *
 * Usage:
 * ```
 * val message = LocalizedString.Resource(R.string.teachers_register_success)
 * val dynamicMessage = LocalizedString.Raw("Error: Connection failed")
 * ```
 *
 * @author Salim Ramirez
 */
sealed class LocalizedString {
    /**
     * A string resource defined in strings.xml that will be localized.
     *
     * @property resourceId The resource ID (e.g., R.string.my_string)
     */
    data class Resource(@param:StringRes val resourceId: Int) : LocalizedString()

    /**
     * A raw string that won't be localized.
     * Useful for dynamic content like error messages from the server.
     *
     * @property value The raw string value
     */
    data class Raw(val value: String) : LocalizedString()

    /**
     * A formatted string resource with arguments.
     *
     * @property resourceId The resource ID (e.g., R.string.teachers_found_count)
     * @property args The format arguments
     */
    data class Formatted(@param:StringRes val resourceId: Int, val args: Array<Any>) : LocalizedString() {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Formatted

            if (resourceId != other.resourceId) return false
            if (!args.contentEquals(other.args)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = resourceId
            result = 31 * result + args.contentHashCode()
            return result
        }
    }
}

