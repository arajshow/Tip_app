package com.arajshow.tipcalculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    cal_button.setOnClickListener { calculateTip() }
    }


    private fun calculateTip() {

        val stringInTextField = costOFService_edit_text.text.toString()
//        That doesn't work, though—toDouble() needs to be called on a String.
//        It turns out that the text attribute of an EditText is an Editable,
//        because it represents text that can be changed.
//        Fortunately, you can convert an Editable to a String by calling toString() on it.
        val cost = stringInTextField.toDoubleOrNull()
        val selectedId= tipOptions.checkedRadioButtonId
        val tipPercentage = when (selectedId) {
            R.id.twenty_percent -> 0.20
            R.id.eighteen_percent -> 0.18
            else -> 0.15
        }

        if (cost == null) {
            tip_result.text = ""
            return
        }

        var tip = tipPercentage * cost
        val roundUp = round_up_switch.isChecked
        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        tip_result.text = getString(R.string.tip_amount, formattedTip)

    }

}