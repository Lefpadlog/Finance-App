package com.lefpadlog.financeapp.code.data.settings

import androidx.lifecycle.LiveData

class SettingsRepository(private val settingsDao: SettingsDao) {

    val getDefaultSettings: LiveData<Settings> = settingsDao.getDefaultSettings()

    suspend fun updateDefaultSettings(settings: Settings) {
        settingsDao.updateDefaultSettings(settings)
    }

}