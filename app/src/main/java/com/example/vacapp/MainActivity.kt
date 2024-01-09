package com.example.vacapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.vacapp.databinding.ActivityMainBinding
import com.example.vacapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.welcomeMessage.observe(this) { message ->
            binding.textView.text = message
        }

        binding.buttonNext.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        val sharedPreferences = getSharedPreferences("CurrencyRates", MODE_PRIVATE)
        val lastSavedCurrency = sharedPreferences.getString("LastSavedCurrency", "CZK") // Načtení poslední uložené měny
        val savedRate = sharedPreferences.getFloat(lastSavedCurrency, 0f)
        binding.savedCurrencyRate.text = "Uložený kurz $lastSavedCurrency: $savedRate"
    }
}
