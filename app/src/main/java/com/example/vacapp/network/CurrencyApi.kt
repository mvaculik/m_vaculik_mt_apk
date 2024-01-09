package com.example.vacapp.network

import com.example.vacapp.model.CurrencyResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CurrencyApi {
    @Headers("apikey: cur_live_KZWMzMV0FvgkELx0faCNtkHdPH0bbyKMH5kAsOzP")
    @GET("latest")
    suspend fun getLatestRates(@Query("base") base: String): CurrencyResponse
}
