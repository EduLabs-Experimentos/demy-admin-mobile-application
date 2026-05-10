package com.nistra.demy.admins.core.common

/**
 * Interface for managing localized strings in a type-safe manner.
 *
 * This interface provides methods to resolve [LocalizedString] instances
 * to their final string representation, respecting the user's locale.
 *
 * Using this approach instead of string keys with reflection:
 * - Type-safe at compile time
 * - Better performance (no reflection)
 * - Build optimizations enabled
 * - ProGuard/R8 friendly
 * - Easier to refactor
 *
 * @author Salim Ramirez
 */
interface LocaleManager {
    /**
     * Resolves a [LocalizedString] to its final string representation.
     *
     * @param localizedString The localized string to resolve
     * @return The resolved string in the current locale
     */
    fun getString(localizedString: LocalizedString): String
}
