package com.example.datetime

import android.icu.util.Calendar
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.timepicker.TimeFormat
import java.text.DateFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val calender=Calendar.getInstance().time
        val dateFormat=DateFormat.getDateInstance(DateFormat.FULL).format(calender)
        val timeFormat=DateFormat.getTimeInstance().format(calender)

        val dateview=findViewById<TextView>(R.id.text_date)
        val timeview=findViewById<TextView>(R.id.text_time)
        timeview.text=timeFormat
        dateview.text=dateFormat





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}