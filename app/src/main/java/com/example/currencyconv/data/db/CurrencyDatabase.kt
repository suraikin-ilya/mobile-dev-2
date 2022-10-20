package com.example.currencyconv.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.currencyconv.data.db.model.CurrencyModel
import com.example.currencyconv.data.db.model.HistoryModel

@Database(
    entities = [CurrencyModel::class, HistoryModel::class], version = 2, exportSchema = false
)
abstract class CurrencyDatabase : RoomDatabase() {
    
    abstract val currencyDao: CurrencyDao
    
    companion object {
        @Volatile
        private var INSTANCE: CurrencyDatabase? = null
        private const val DATABASE_NAME = "currency_database"
        
        fun getDatabase(context: Context): CurrencyDatabase {
            var tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CurrencyDatabase::class.java,
                    DATABASE_NAME
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}