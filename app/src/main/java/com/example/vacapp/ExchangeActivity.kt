package com.example.vacapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vacapp.databinding.ActivityExchangeHistoryBinding

class ExchangeHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExchangeHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExchangeHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("ExchangeHistory", MODE_PRIVATE)
        val history = sharedPreferences.getString("history", "")

        // Zobrazení historie v TextView
        binding.textViewHistory.text = history

        binding.btnBackToMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnClearHistory.setOnClickListener {
            clearHistory()
        }
    }

    private fun clearHistory() {
        val sharedPreferences = getSharedPreferences("ExchangeHistory", MODE_PRIVATE)
        sharedPreferences.edit().remove("history").apply()
        binding.textViewHistory.text = ""
    }
}
