package com.example.diceroller

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.example.diceroller.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityMainBinding
    private val viewModel : DiceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        ViewCompat.setOnApplyWindowInsetsListener(binding.topBar) { v, insets ->
            val topInset = insets.getInsets(WindowInsetsCompat.Type.systemBars()).top
            v.setPadding(v.paddingLeft, topInset, v.paddingRight, v.paddingBottom)
            insets
        }

        binding.rollButton.setOnClickListener {
            viewModel.rollDice()

            val diceLeft = viewModel.diceLeft.value
            val diceRight = viewModel.diceRight.value

            if(diceLeft == diceRight){
                Toast.makeText(this, "Selamat anda dapat dadu double!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Anda belum beruntung!", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.diceLeft.observe(this, Observer { number ->
            binding.diceLeft.setImageResource(getDiceImage(number))
        })

        viewModel.diceRight.observe(this, Observer { number ->
            binding.diceRight.setImageResource(getDiceImage(number))
        })
    }

    private fun getDiceImage(number: Int): Int {
        return when (number) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            6 -> R.drawable.dice_6
            else -> throw IllegalArgumentException("Invalid dice number")
        }
    }
}