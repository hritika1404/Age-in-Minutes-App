package com.example.agecalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    var selectedDate: TextView? = null
    var ageInMins : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener {
            datePicker()

        }
    }
    fun datePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        selectedDate = findViewById(R.id.selectedDate)
        ageInMins = findViewById(R.id.ageCal)
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                val selection = "$selectedDayOfMonth.${selectedMonth+1}.$selectedYear"
                selectedDate?.text = selection
                val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selection)
                theDate?.let {
                    val selectedDateInMin = theDate.time / 60000
                    val currDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currDate?.let {
                        val currDateInMin = currDate.time / 60000
                        val difference = currDateInMin - selectedDateInMin
                        ageInMins?.text = difference.toString()
                    }
                }
            }, year, month, day)
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

    }

}