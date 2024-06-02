package com.lefpadlog.financeapp.code.data.settings

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface SettingsDao {


    @Update
    suspend fun updateDefaultSettings(settings: Settings)

    @Query("SELECT * FROM settings_table WHERE id = 1")
    fun getDefaultSettings(): LiveData<Settings>
}