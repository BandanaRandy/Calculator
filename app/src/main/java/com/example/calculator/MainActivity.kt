package com.example.calculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var tvCalcDisplay: TextView? = null
    var lastNumeric: Boolean = false
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCalcDisplay = findViewById(R.id.tvCalcDisplay)
    }
    fun onDigit(view: View) {
        tvCalcDisplay?.append((view as Button).text)
        lastNumeric = true
        lastDot = false


    }

    fun onClear(view: View){
        tvCalcDisplay?.text = ""
    }

    fun onDecimalPoint(view: View){
        if(lastNumeric && !lastDot){
            tvCalcDisplay?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    @SuppressLint("SuspiciousIndentation")
    fun onOperator(view: View) {
        tvCalcDisplay?.text?.let {


            if (lastNumeric && !isOperatorAdded(it.toString()))
                tvCalcDisplay?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
        }
    }

    fun onEqual(view: View){
        if(lastNumeric){
            var tvValue = tvCalcDisplay?.text.toString()
            try{
                val splitValue = tvValue.split("-")
                var one = splitValue[0]
                var two = splitValue[1]
                tvCalcDisplay?.text = (one.toDouble() - two.toDouble()).toString()


            } catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }
    private fun isOperatorAdded(value: String) : Boolean {
        return if (value.startsWith("-")) {
            false
        }else{
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
                    || value.contains("√")
        }
    }
}