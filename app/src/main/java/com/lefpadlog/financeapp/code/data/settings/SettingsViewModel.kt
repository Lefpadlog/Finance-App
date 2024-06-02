package com.lefpadlog.financeapp.code.data.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.lefpadlog.financeapp.code.data.AppDatabase.mainActivity
import com.lefpadlog.financeapp.code.date.convertDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    val getDefaultSettings: LiveData<Settings>
    private val repository: SettingsRepository

    init {
        val paymentMethodDao = SettingsDatabase.getDatabase(application).settingsDao()
        repository = SettingsRepository(paymentMethodDao)
        getDefaultSettings = repository.getDefaultSettings
    }

    fun getDefault(): Settings {
        var settings = Settings(1, convertDate(LocalDate.now().minusDays(15)))
        getDefaultSettings.observe(mainActivity) {
            if (it != null)
                settings = it
        }
        return settings
    }

    fun updateDefault(settings: Settings) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateDefaultSettings(settings)
        }
    }

}