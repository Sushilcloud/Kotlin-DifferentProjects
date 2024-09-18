package com.example.autocompletetextview

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.autocompletetextview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

 private lateinit var binding: ActivityMainBinding
 private var language= arrayOf("C","Java","JavaScript","c++","perl","kotlin","Net","C#","PHP","Python","R","SQL","Swift",)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // we need a adapter
        val adapter = ArrayAdapter(this, R.layout.simple_list_item_1,language)
        binding.actv.threshold=1
        binding.actv.setAdapter(adapter)




    }
}