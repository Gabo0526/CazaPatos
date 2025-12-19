package com.vasconez.gabriel.cazapatos

import android.app.Activity
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class EncryptedSharedPreferencesManager(
    private val activity: Activity
) : FileHandler {

    private val masterKey = MasterKey.Builder(activity)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val sharedPreferences = EncryptedSharedPreferences.create(
        activity,
        "encrypted_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    override fun SaveInformation(datosAGrabar: Pair<String, String>) {
        sharedPreferences.edit()
            .putString(LOGIN_KEY, datosAGrabar.first)
            .putString(PASSWORD_KEY, datosAGrabar.second)
            .apply()
    }

    override fun ReadInformation(): Pair<String, String> {
        val email = sharedPreferences.getString(LOGIN_KEY, "") ?: ""
        val password = sharedPreferences.getString(PASSWORD_KEY, "") ?: ""
        return email to password
    }
}
