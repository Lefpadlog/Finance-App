package com.lefpadlog.financeapp.code.data.payment

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Entity(tableName = "payment_table")
data class Payment(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var title: String,
    var description: String,
    var date: String = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
    var type: String = "None",
    var from: String = "None",
    var to: String = "None",
    var amount: Float = 0f,
    var interval: String = "Once",
    var originalId: Int? = null,
    var lastIntervalDate: String? = if (originalId == null && interval != "Once") date else null
)