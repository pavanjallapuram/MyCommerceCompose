package com.example.mycommercejetpack.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import com.example.mycommercejetpack.R
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


object Util {

    const val BASE_URL = "https://fakestoreapi.com/"

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    private val IS_LOGGED_IN_KEY = booleanPreferencesKey("is_logged_in")

    suspend fun saveLoginStatus(context: Context, status: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN_KEY] = status
        }
    }

    fun getLoginStatus(context: Context): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[IS_LOGGED_IN_KEY] ?: false
        }
    }

    fun isValidEmail(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.matches(emailPattern.toRegex())
    }


}