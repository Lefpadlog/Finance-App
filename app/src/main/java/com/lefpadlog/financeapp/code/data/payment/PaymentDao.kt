package com.lefpadlog.financeapp.code.data.payment

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface PaymentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPayment(payment: Payment)

    @Update
    suspend fun updatePayment(payment: Payment)

    @Delete
    suspend fun removePayment(payment: Payment)

    @Query("SELECT * FROM payment_table ORDER BY id ASC")
    fun getAllPayments(): LiveData<List<Payment>>

    @Query("SELECT * FROM payment_table WHERE interval!='Once' AND originalId IS NULL")
    fun getRepeatedPayments(): LiveData<List<Payment>>

}