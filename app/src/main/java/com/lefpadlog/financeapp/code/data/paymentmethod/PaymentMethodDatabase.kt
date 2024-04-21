package com.lefpadlog.financeapp.code.data.paymentmethod

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PaymentMethod::class], version = 1)
abstract class PaymentMethodDatabase : RoomDatabase() {

    abstract fun paymentMethodDao(): PaymentMethodDao

    companion object {
        @Volatile
        private var INSTANCE: PaymentMethodDatabase? = null

        fun getDatabase(context: Context): PaymentMethodDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null)
                return tempInstance

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PaymentMethodDatabase::class.java,
                    "payment_method_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }

}