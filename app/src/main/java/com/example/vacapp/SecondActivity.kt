package com.example.vacapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.vacapp.databinding.ActivitySecondBinding
import com.example.vacapp.viewmodel.SecondViewModel

class SecondActivity : AppCompatActivity() {
    private val viewModel: SecondViewModel by viewModels()
    private lateinit var binding: ActivitySecondBinding
    private val czkRate = 25.0 // Předpokládá se, že 1 EUR = 25 CZK

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.currencyRates.observe(this) { rates ->
            rates.forEach { (code, rate) ->
                val linearLayout = LinearLayout(this).apply {
                    orientation = LinearLayout.HORIZONTAL
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                }

                val textView = TextView(this).apply {
                    text = "$code/CZK: ${rate.value * czkRate}"
                    layoutParams = LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1f
                    )
                }

                val button = Button(this).apply {
                    text = "Investovat"
                    setOnClickListener {
                        saveCurrencyRate(code, rate.value * czkRate)
                    }
                }

                linearLayout.addView(textView)
                linearLayout.addView(button)
                binding.currenciesContainer.addView(linearLayout)
            }
        }

        binding.buttonBack.setOnClickListener {
            finish() // Ukončí aktuální aktivitu a vrátí se zpět na MainActivity
        }
    }

    private fun saveCurrencyRate(code: String, rate: Double) {
        val sharedPreferences = getSharedPreferences("CurrencyRates", MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putFloat(code, rate.toFloat())
            putString("LastSavedCurrency", code)
            apply()
        }
        Log.d("SecondActivity", "Uložen kurz pro $code: ${rate.toFloat()}")
    }

}
