package com.example.vacapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.vacapp.databinding.ActivitySecondBinding
import com.example.vacapp.viewmodel.SecondViewModel

class SecondActivity : AppCompatActivity() {
    private val viewModel: SecondViewModel by viewModels()
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Pozorování LiveData z ViewModelu a aktualizace UI
        viewModel.euroRate.observe(this) { rate ->
            // Předpokládám, že máte TextView s ID euroRateText ve vašem layoutu
            binding.euroRateText.text = "EUR: ${rate.value}"
        }
    }
}
