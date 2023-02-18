package com.example.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.btnBirthday)

        btn.setOnClickListener { view ->
            datePicker(view)
        }
    }
    
    fun datePicker(view: View){

        val calender = Calendar.getInstance()
        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH)
        val dayOfMonth = calender.get(Calendar.DAY_OF_MONTH)


        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
//                Toast.makeText(this, "Date is $dayOfMonth/${month+1}/$year", Toast.LENGTH_SHORT).show()

                val selectedDate = "$dayOfMonth/${month+1}/$year"
                showDayTextView.text = selectedDate

                val simpleDate = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val date = simpleDate.parse(selectedDate)

                val seconds = date!!.time / 1000

                val toDateInSeconds = simpleDate.parse(simpleDate.format(System.currentTimeMillis()))!!.time / 1000

                val trueTimeInSeconds = toDateInSeconds - seconds

                inSecondsTxtView.text = "$trueTimeInSeconds s"
                inMinutesTxtView.text = "${trueTimeInSeconds / 60} M"
                inHoursTxtView.text = "${trueTimeInSeconds / 60 /60} hrs"
                inDaysTxtView.text = "${trueTimeInSeconds / 86400} days"
                inYrsTxtView.text = "${trueTimeInSeconds / 31536000} yrs"


            }, year, month, dayOfMonth
        )
        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()
    }
}