package com.example.currencyconv.data.api

import com.example.currencyconv.data.RemoteDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        
        Retrofit.Builder()
            .client(client)
            .baseUrl("http://jsonserver.std-1527.ist.mospolytech.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private val api: CurrencyApi by lazy {
        retrofit.create(CurrencyApi::class.java)
    }
    
    val remoteDataSource: RemoteDataSource by lazy { RemoteDataSource(api) }
}