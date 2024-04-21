package com.lefpadlog.financeapp.code.data.paymentmethod

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.lefpadlog.financeapp.code.data.payment.Payment

@Dao
interface PaymentMethodDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPaymentMethod(paymentMethod: PaymentMethod)

    @Update
    suspend fun updatePaymentMethod(paymentMethod: PaymentMethod)

    @Delete
    suspend fun removePaymentMethod(paymentMethod: PaymentMethod)

    @Query("SELECT * FROM payment_method_table ORDER BY title ASC")
    fun getAllPaymentMethods(): LiveData<List<PaymentMethod>>

}