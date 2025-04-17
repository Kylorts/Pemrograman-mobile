package com.example.diceroller

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class DiceViewModel : ViewModel() {
    private val _diceLeft = MutableLiveData<Int>()
    private val _diceRight = MutableLiveData<Int>()

    val diceLeft : LiveData<Int> =_diceLeft
    val diceRight : LiveData<Int> = _diceRight

    fun rollDice() {
        _diceRight.value = Random.nextInt(1, 7)
        _diceLeft.value = Random.nextInt(1, 7)
    }
}