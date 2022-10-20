package com.example.currencyconv.data.db

import androidx.room.*
import com.example.currencyconv.data.db.model.HistoryModel

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveTransaction(historyModel: HistoryModel)
    
    @Query("SELECT * FROM HistoryModel ORDER BY date DESC")
    fun readAllHistory(): List<HistoryModel>

}