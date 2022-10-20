package com.example.currencyconv.repository

import android.util.Log
import com.example.currencyconv.data.LocalDataSource
import com.example.currencyconv.data.RemoteDataSource
import com.example.currencyconv.data.db.model.CurrencyModel
import com.example.currencyconv.data.db.model.HistoryModel

class Repository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {
    
    suspend fun getCurrencies(): List<CurrencyModel> {
        val response = remoteDataSource.getCurrencies()
        if (response.isSuccessful) {
            response.body()?.let { currencyResponse ->
                return currencyResponse.rates.map { currency ->
                    CurrencyModel(
                        name = currency.key,
                        value = currency.value,
                        chosen = false
                    )
                }
            }
        } else {
            Log.d("MyLog", "Не получилось взять валюты из интернета")
            Log.d("MyLog", response.errorBody().toString())
        }
        return emptyList()
    }

    
    fun saveTransaction(history: HistoryModel) {
        localDataSource.saveTransaction(history)
    }
    
    fun readAllHistory(): List<HistoryModel> {
        return localDataSource.readAllHistory()
    }
}