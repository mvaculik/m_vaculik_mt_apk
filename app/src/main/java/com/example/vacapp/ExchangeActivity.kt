package com.example.vacapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.vacapp.databinding.ActivityExchangeHistoryBinding

class ExchangeHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExchangeHistoryBinding
    private lateinit var viewModel: ExchangeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExchangeHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(ExchangeViewModel::class.java)

        viewModel.history.observe(this) { history ->
            binding.textViewHistory.text = history
        }

        binding.btnBackToMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnClearHistory.setOnClickListener {
            viewModel.clearHistory()
        }

        viewModel.loadHistory()
    }
}
