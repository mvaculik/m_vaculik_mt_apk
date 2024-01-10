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

        // Přidá observer pro aktuální zůstatek účtu
        viewModel.currentAccountBalance.observe(this) { message ->
            binding.textView.text = message
        }
    }

    override fun onResume() {
        super.onResume()

        val sharedPreferences = getSharedPreferences("CurrencyRates", MODE_PRIVATE)
        val lastSavedCurrency = sharedPreferences.getString("LastSavedCurrency", "CZK")
        val savedRate = sharedPreferences.getFloat(lastSavedCurrency, 0f)
        binding.savedCurrencyRate.text = "USD/$lastSavedCurrency: ${String.format("%.7f", savedRate)}"

        binding.exchangeButton.setOnClickListener {
            val amountText = binding.amountToExchange.text.toString()
            if (amountText.isNotEmpty()) {
                val amount = amountText.toDouble()
                val convertedAmount = amount * savedRate
                binding.exchangeResult.text = "${String.format("%.7f", convertedAmount)} $lastSavedCurrency"
            } else {
                binding.exchangeResult.text = "Zadejte částku"
            }
        }
    }
}
