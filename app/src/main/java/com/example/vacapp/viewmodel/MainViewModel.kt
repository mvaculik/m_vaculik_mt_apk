package com.example.vacapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    // Příklad LiveData, která uchovává zprávu pro UI
    private val _welcomeMessage = MutableLiveData<String>()
    val welcomeMessage: LiveData<String> get() = _welcomeMessage

    init {
        // Inicializujte vaši LiveData zprávou
        _welcomeMessage.value = "Vítejte v VacApp!"
    }

    // Zde můžete přidat další funkce, jako jsou metody pro načítání dat atd.
}
