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

        // Zde byste mohli použít viewModel k nahrání dat a pozorování LiveData
        // Například:
        // viewModel.someLiveData.observe(this) { data ->
        //     binding.someTextView.text = data
        // }
    }
}
