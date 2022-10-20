package com.example.currencyconv

import android.app.Application

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        DependencyInjection.initCurrencyDao(this)
        DependencyInjection.initLocalDataSource(DependencyInjection.currencyDao)
        DependencyInjection.initCurrencyRepository(DependencyInjection.localDataSource)
    }
}