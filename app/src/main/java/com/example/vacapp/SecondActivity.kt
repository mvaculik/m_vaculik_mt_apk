package com.example.vacapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.vacapp.databinding.ActivitySecondBinding
import com.example.vacapp.viewmodel.SecondViewModel
import com.example.vacapp.model.CurrencyRate

class SecondActivity : AppCompatActivity() {
    private val viewModel: SecondViewModel by viewModels()
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val searchEditText = findViewById<EditText>(R.id.searchEditText)

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.currencyRates.value?.let { rates ->
                    filterCurrencies(s.toString(), rates)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        viewModel.currencyRates.observe(this) { rates ->
            filterCurrencies(searchEditText.text.toString(), rates)
        }

        binding.buttonBack.setOnClickListener {
            finish() // Ukončí aktuální aktivitu a vrátí se zpět na MainActivity
        }
    }

    private fun filterCurrencies(searchText: String, rates: Map<String, CurrencyRate>) {
        binding.currenciesContainer.removeAllViews()

        rates.filter { it.key.contains(searchText, ignoreCase = true) }
            .forEach { (code, rate) ->
                val linearLayout = LinearLayout(this).apply {
                    orientation = LinearLayout.HORIZONTAL
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                }

                val textView = TextView(this).apply {
                    text = "USD/$code: ${String.format("%.7f", rate.value)}"
                    layoutParams = LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1f
                    )
                }

                val button = Button(this).apply {
                    text = "Zvolit"
                    setOnClickListener {
                        saveCurrencyRate(code, rate.value)
                    }
                }

                linearLayout.addView(textView)
                linearLayout.addView(button)
                binding.currenciesContainer.addView(linearLayout)
            }
    }

    private fun saveCurrencyRate(code: String, rate: Double) {
        val sharedPreferences = getSharedPreferences("CurrencyRates", MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putFloat(code, rate.toFloat())
            putString("LastSavedCurrency", code)
            apply()
        }
        Log.d("SecondActivity", "Uložen kurz pro $code: ${String.format("%.7f", rate)}")
    }
}
