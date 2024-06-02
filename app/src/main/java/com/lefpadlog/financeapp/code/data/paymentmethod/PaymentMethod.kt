package com.lefpadlog.financeapp.code.data.paymentmethod

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "payment_method_table")
data class PaymentMethod(
    @PrimaryKey
    var title: String,
    var description: String,
    var information: String = "",
    var type: String = "None",
    var amount: Float = 0f
)