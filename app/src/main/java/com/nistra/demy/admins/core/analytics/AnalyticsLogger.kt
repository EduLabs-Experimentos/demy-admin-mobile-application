package com.nistra.demy.admins.core.analytics

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnalyticsLogger @Inject constructor(
    private val firebaseAnalytics: FirebaseAnalytics
) {
    fun logEvent(name: String, params: Bundle = Bundle()) {
        firebaseAnalytics.logEvent(name, params)
    }
}
