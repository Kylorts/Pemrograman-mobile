package com.example.tipcalculator

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.example.tipcalculator.databinding.ActivityMainBinding
import com.example.tipcalculator.viewmodel.TipViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityMainBinding
    private  val viewModel: TipViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //window.statusBarColor = getColor(R.color.black)
        ViewCompat.setOnApplyWindowInsetsListener(binding.topBar) { v, insets ->
            val topInset = insets.getInsets(WindowInsetsCompat.Type.systemBars()).top
            v.setPadding(v.paddingLeft, topInset, v.paddingRight, v.paddingBottom)
            insets
        }

        viewModel.tipAmount.observe(this, Observer { tip ->
            binding.tipResult.text = tip
        })

        binding.calculateButton.setOnClickListener {
            val cost = binding.costService.text.toString().toDoubleOrNull()

            if(cost == null || cost < 0.0){
                Toast.makeText(this, "Silahkan masukkan harga yang valid!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            hideKeyboard()

            viewModel.setCostService(cost)
            val tipPercent = when (binding.tipOption.checkedRadioButtonId){
                R.id.optionAmazing -> 0.20
                R.id.optionGood -> 0.18
                R.id.optionOkay -> 0.15
                else -> 0.15
            }

            viewModel.setTipPercent(tipPercent)
            viewModel.setRoundUp(binding.roundTip.isChecked)
            viewModel.calculateTip()
        }

        binding.costService.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding.calculateButton.performClick()
                hideKeyboard()
                true
            } else {
                false
            }
        }
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.costService.windowToken, 0)
    }
}