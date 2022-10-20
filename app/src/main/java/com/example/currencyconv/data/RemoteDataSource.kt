package com.example.currencyconv.data

import com.example.currencyconv.data.api.CurrencyApi
import com.example.currencyconv.data.api.CurrencyResponse
import retrofit2.Response

class RemoteDataSource(private val api: CurrencyApi) {
    
    suspend fun getCurrencies(): Response<CurrencyResponse> {
        return api.getCurrencies()
    }
    
}