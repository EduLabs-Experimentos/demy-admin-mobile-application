package com.nistra.demy.admins.core.localization

import android.content.Context
import com.nistra.demy.admins.core.common.LocaleManager
import com.nistra.demy.admins.core.common.LocalizedString
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Android implementation of [LocaleManager] using Context resources.
 *
 * This implementation resolves [LocalizedString] instances to their
 * final string representation using Android's resource system, which
 * automatically handles localization based on the device's locale.
 *
 * Benefits over reflection-based approach:
 * - No use of getIdentifier() (much faster)
 * - Compile-time verification of resource IDs
 * - ProGuard/R8 can optimize better
 * - Type-safe at compile time
 *
 * @param context Application context for accessing resources
 *
 * @author Salim Ramirez
 */
class AndroidLocaleManager @Inject constructor(
    @param:ApplicationContext private val context: Context
) : LocaleManager {

    override fun getString(localizedString: LocalizedString): String {
        return when (localizedString) {
            is LocalizedString.Resource -> {
                context.getString(localizedString.resourceId)
            }
            is LocalizedString.Raw -> {
                localizedString.value
            }
            is LocalizedString.Formatted -> {
                context.getString(localizedString.resourceId, *localizedString.args)
            }
        }
    }
}

