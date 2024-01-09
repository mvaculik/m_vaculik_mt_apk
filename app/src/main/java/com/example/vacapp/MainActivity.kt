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

        // Inicializace View Bindingu
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Pozorování LiveData z ViewModelu a aktualizace UI
        viewModel.welcomeMessage.observe(this) { message ->
            binding.textView.text = message
        }

        // Nastavení onClickListeneru pomocí View Bindingu
        binding.buttonNext.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }
}
