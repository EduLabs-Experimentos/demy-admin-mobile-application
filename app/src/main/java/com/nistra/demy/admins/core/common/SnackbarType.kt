package com.nistra.demy.admins.core.common

/**
 * Defines the semantic types for snackbar messages.
 *
 * Each type corresponds to a specific color scheme from the Material Design
 * extended colors palette, providing visual feedback that matches the message intent.
 *
 * This enum belongs to the common/presentation layer, not the UI layer,
 * allowing ViewModels to use it without depending on UI components.
 *
 * @author Salim Ramirez
 */
enum class SnackbarType {
    /**
     * Success type - for successful operations.
     * Uses green color scheme from extended colors.
     */
    SUCCESS,

    /**
     * Error type - for system errors or failed operations.
     * Uses red color scheme from Material error colors.
     */
    ERROR,

    /**
     * Warning type - for validation errors or cautionary messages.
     * Uses amber/yellow color scheme from extended colors.
     */
    WARNING,

    /**
     * Info type - for informational messages.
     * Uses blue color scheme from extended colors.
     */
    INFO
}

