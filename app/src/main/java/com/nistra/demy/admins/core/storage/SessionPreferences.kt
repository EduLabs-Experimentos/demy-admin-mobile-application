package com.nistra.demy.admins.core.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionPreferences @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    companion object {
        private val KEY_ID = stringPreferencesKey("user_id")
        private val KEY_EMAIL = stringPreferencesKey("user_email")
        private val KEY_TOKEN = stringPreferencesKey("user_token")
        private val KEY_ADMIN_ID = stringPreferencesKey("administrator_id")
        private val KEY_ACADEMY_ID = stringPreferencesKey("academy_id")
    }

    val token: Flow<String?> = dataStore.data.map { it[KEY_TOKEN] }
    val email: Flow<String?> = dataStore.data.map { it[KEY_EMAIL] }
    val userId: Flow<String?> = dataStore.data.map { it[KEY_ID] }
    val administratorId: Flow<String?> = dataStore.data.map { it[KEY_ADMIN_ID] }
    val academyId: Flow<String?> = dataStore.data.map { it[KEY_ACADEMY_ID] }

    suspend fun saveSession(id: String, email: String, token: String) {
        dataStore.edit { prefs ->
            prefs[KEY_ID] = id
            prefs[KEY_EMAIL] = email
            prefs[KEY_TOKEN] = token
        }
    }

    suspend fun saveToken(token: String) {
        dataStore.edit { prefs ->
            prefs[KEY_TOKEN] = token
        }
    }

    suspend fun saveUserId(userId: String) {
        dataStore.edit { prefs ->
            prefs[KEY_ID] = userId
        }
    }

    suspend fun saveAdministratorId(adminId: Long) {
        dataStore.edit { prefs ->
            prefs[KEY_ADMIN_ID] = adminId.toString()
        }
    }

    suspend fun saveAcademyId(id: Long) {
        dataStore.edit { prefs ->
            prefs[KEY_ACADEMY_ID] = id.toString()
        }
    }

    suspend fun clearSession() {
        dataStore.edit { prefs ->
            prefs.clear()
        }
    }

    suspend fun getToken(): String? = dataStore.data.map { it[KEY_TOKEN] }.first()

    suspend fun getUserId(): String? = dataStore.data.map { it[KEY_ID] }.first()

    suspend fun getAdministratorId(): String? = dataStore.data.map { it[KEY_ADMIN_ID] }.first()

    suspend fun getAcademyId(): String? = dataStore.data.map { it[KEY_ACADEMY_ID] }.first()
}
