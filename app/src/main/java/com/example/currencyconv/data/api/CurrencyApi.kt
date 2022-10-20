package com.example.currencyconv.data.api

import retrofit2.Response
import retrofit2.http.GET

interface CurrencyApi {
    @GET("/db.json")
    suspend fun getCurrencies(): Response<CurrencyResponse>
}