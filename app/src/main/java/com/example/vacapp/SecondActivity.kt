package com.example.vacapp

import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.vacapp.databinding.ActivitySecondBinding
import com.example.vacapp.viewmodel.SecondViewModel

class SecondActivity : AppCompatActivity() {
    private val viewModel: SecondViewModel by viewModels()
    private lateinit var binding: ActivitySecondBinding
    private val czkRate = 25.0 // Předpokládáme, že 1 EUR = 25 CZK

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.currencyRates.observe(this) { rates ->
            rates.forEach { (code, rate) ->
                val textView = TextView(this).apply {
                    text = "$code: ${rate.value}, v CZK: ${rate.value * czkRate}"
                    textSize = 16f
                    setTextColor(Color.BLACK)
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                }
                binding.currenciesContainer.addView(textView)
            }
        }
    }
}
