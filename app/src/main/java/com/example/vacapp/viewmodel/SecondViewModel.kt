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

    private val _euroRate = MutableLiveData<CurrencyRate>()
    val euroRate: LiveData<CurrencyRate> = _euroRate

    init {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getLatestRates("EUR")
                _euroRate.postValue(response.data["EUR"])
                Log.d("SecondViewModel", "Kurz EUR: ${response.data["EUR"]}")
            } catch (e: Exception) {
                Log.e("SecondViewModel", "Chyba při získávání kurzu ${RetrofitInstance.api}", e)
            }
        }
    }
}
