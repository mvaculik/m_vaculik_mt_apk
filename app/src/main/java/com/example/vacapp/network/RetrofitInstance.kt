package com.example.vacapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: CurrencyApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.currencyapi.com/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CurrencyApi::class.java)
    }
}