package com.example.currencyconv.data

import com.example.currencyconv.data.db.CurrencyDao
import com.example.currencyconv.data.db.model.HistoryModel


class LocalDataSource(private val currencyDao: CurrencyDao) {

    fun saveTransaction(historyModel: HistoryModel) {
        currencyDao.saveTransaction(historyModel)
    }
    
    fun readAllHistory(): List<HistoryModel> {
        return currencyDao.readAllHistory()
    }

}