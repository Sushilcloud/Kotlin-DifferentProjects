package com.example.view_binding_in_kotlin

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.view_binding_in_kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // attach xml to this activity
        binding= ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)

        binding.clickhere.setOnClickListener {
            binding.clickhere.text = "Clicked"
            Toast.makeText(this@MainActivity, "clicked here", Toast.LENGTH_SHORT).show()
        }

    }
}