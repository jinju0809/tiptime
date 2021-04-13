package com.jjyoun.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jjyoun.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        //val stringInTextField = binding.costOfService.text
        //작동하지 않음. toDouble()은 String 을 필요로 하는데 위는 아직 Editable 한 EditText 이다.
        //val cost = stringInTextField.toDouble()

        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if(cost == null) {
            binding.tipResult.text = ""
            return
        }
        //이렇게 해주어야 한다.

        //RadioGroup 값 가져오기
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage * cost
        //val 이 아니라 var 을 쓴 이유는 유저가 반올림을 선택하냐 마냐에 따라 값이 바뀔 수 있기 때문.

        //switch 요소에 대해서는 isChecked 속성으로 체크된 값을 가져옴
        if (binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip*0.01)*100
            //tip = ceil(tip)
            //그냥 ceil() 함수를 쓸 수 있는데,,, 임포트를 해야하고
            // 지금같은 경우는 kotlin.math.ceil()처럼 써주는게 더 간단하다고..?
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)

    }



}//main
