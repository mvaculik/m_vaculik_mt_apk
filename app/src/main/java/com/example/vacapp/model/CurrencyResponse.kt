package com.example.vacapp.model

data class CurrencyRate(
    val code: String,
    val value: Double
)

data class CurrencyResponse(
    val data: Map<String, CurrencyRate>
)
