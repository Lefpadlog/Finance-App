package com.lefpadlog.financeapp.code.data.settings

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings_table")
data class Settings(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var lastChecked: String
)