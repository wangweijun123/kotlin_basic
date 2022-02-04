package com.wangweijun.myapplication.tip

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.wangweijun.myapplication.R
import com.wangweijun.myapplication.databinding.ActivityTipTimeBinding
import java.text.NumberFormat

class RecycleViewActivity : AppCompatActivity(){
    private lateinit var binding: ActivityTipTimeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTipTimeBinding.inflate(LayoutInflater.from(applicationContext))
        setContentView(binding.root)

//        binding.calculateButton.text = "a button"
        binding.calculateButton.setOnClickListener {
            calculateTip()
        }
    }

    fun calculateTip() {
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDouble()
        val selectedId = binding.tipOptions.checkedRadioButtonId
        val tipPercentage = when (selectedId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage * cost
        val roundUp = binding.roundUpSwitch.isChecked
        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}