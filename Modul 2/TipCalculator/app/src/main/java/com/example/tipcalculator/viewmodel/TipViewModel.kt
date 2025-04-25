package com.example.tipcalculator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.ceil

class TipViewModel : ViewModel() {
    private val _tipAmount = MutableLiveData<String>()
    val tipAmount: LiveData<String> get() = _tipAmount

    private var costService = 0.0
    private var tipPercent = 0.20
    private var roundUp = true

    fun setCostService (cost: Double){
        costService = if(cost < 0) 0.0 else cost
    }

    fun setTipPercent(percent: Double){
        tipPercent = percent
    }

    fun setRoundUp(round: Boolean){
        roundUp = round
    }

    fun calculateTip() {
        var tip = costService * tipPercent
        if(roundUp) {
            tip = ceil(tip)
        }

        _tipAmount.value = "Tip Amount: $%.2f" .format(tip)
    }
}