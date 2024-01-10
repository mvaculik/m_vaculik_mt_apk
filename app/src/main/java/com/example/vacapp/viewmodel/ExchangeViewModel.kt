package com.example.vacapp

import android.app.Application
import android.content.Context.MODE_PRIVATE
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ExchangeViewModel(application: Application) : AndroidViewModel(application) {

    private val _history = MutableLiveData<String>()
    val history: LiveData<String> = _history

    fun loadHistory() {
        val sharedPreferences = getApplication<Application>().getSharedPreferences("ExchangeHistory", MODE_PRIVATE)
        _history.value = sharedPreferences.getString("history", "")
    }

    fun clearHistory() {
        val sharedPreferences = getApplication<Application>().getSharedPreferences("ExchangeHistory", MODE_PRIVATE)
        sharedPreferences.edit().remove("history").apply()
        _history.value = ""
    }
}
