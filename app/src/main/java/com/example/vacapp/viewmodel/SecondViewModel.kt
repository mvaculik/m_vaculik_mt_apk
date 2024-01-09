package com.example.vacapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vacapp.model.CurrencyResponse
import com.example.vacapp.network.RetrofitInstance
import kotlinx.coroutines.launch

import com.example.vacapp.model.CurrencyRate

class SecondViewModel : ViewModel() {
    private val _currencyRates = MutableLiveData<Map<String, CurrencyRate>>()
    val currencyRates: LiveData<Map<String, CurrencyRate>> = _currencyRates

    init {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getLatestRates("EUR")
                _currencyRates.postValue(response.data)
            } catch (e: Exception) {
                Log.e("SecondViewModel", "Chyba při získávání kurzů", e)
            }
        }
    }
}

