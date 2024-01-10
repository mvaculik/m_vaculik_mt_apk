package com.example.vacapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _welcomeMessage = MutableLiveData<String>()
    val welcomeMessage: LiveData<String> get() = _welcomeMessage

    private val _currentAccountBalance = MutableLiveData<String>()
    val currentAccountBalance: LiveData<String> get() = _currentAccountBalance

    init {
        _welcomeMessage.value = "Zvolený měnový pár:"
    }

}
