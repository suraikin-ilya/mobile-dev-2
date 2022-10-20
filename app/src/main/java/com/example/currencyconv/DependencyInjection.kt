package com.example.currencyconv

import android.content.Context
import com.example.currencyconv.data.LocalDataSource
import com.example.currencyconv.data.api.RetrofitInstance
import com.example.currencyconv.data.db.CurrencyDao
import com.example.currencyconv.data.db.CurrencyDatabase
import com.example.currencyconv.repository.Repository

object DependencyInjection {
    lateinit var currencyDao: CurrencyDao
    lateinit var localDataSource: LocalDataSource
    lateinit var repository: Repository
    
    fun initCurrencyDao(context: Context) {
        currencyDao = CurrencyDatabase.getDatabase(context).currencyDao
    }
    
    fun initLocalDataSource(currencyDao: CurrencyDao) {
        localDataSource = LocalDataSource(currencyDao)
    }
    
    fun initCurrencyRepository(localDataSource: LocalDataSource) {
        repository = Repository(RetrofitInstance.remoteDataSource, localDataSource)
    }
}