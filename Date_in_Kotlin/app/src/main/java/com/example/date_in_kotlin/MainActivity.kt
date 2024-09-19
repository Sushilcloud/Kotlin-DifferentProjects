package com.example.date_in_kotlin

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.date_in_kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {



    lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
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

        binding.txtDate.setOnClickListener{

        binding.dtPicker.setOnDateChangedListener { _, year, month, dayOfMonth ->
            val date = ("%02d".format(dayOfMonth) + "-" +"%02d".format(month + 1) + "-" + year)
            binding.txtDate.text=date

    }
        }
}
}