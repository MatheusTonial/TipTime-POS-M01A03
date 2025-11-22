package com.tonial.tiptime_pos1_aula3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tonial.tiptime_pos1_aula3.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    //private lateinit var calculate_button: Button
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //calculate_button = findViewById(R.id.calculate_button)
        binding.calculateButton.setOnClickListener {
            calculateTip()
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(0)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)



    }

    private fun calculateTip() {
        //entrada
        val stringInTextField = binding.costOfService.text.toString()
        val cost: Double = stringInTextField.toDoubleOrNull() ?: 0.0

        val selectedId:Int = binding.tipOption.checkedRadioButtonId
        val tipPercentage: Double = when (selectedId) {
            R.id.option_amazing -> 0.2
            R.id.option_good -> 0.18
            //R.id.option_okay -> 0.15
            else -> 0.15
        }

        //process
        var tip: Double = tipPercentage * cost
        val roundUp: Boolean = binding.roundUpSwitch.isChecked

        if(roundUp){
            tip = kotlin.math.ceil(tip)
        }

        //saida
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)

    }
}