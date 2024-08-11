package com.example.latitude_longitude

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : AppCompatActivity() {

    lateinit var btnlocation:Button
    lateinit var btnlocationaddress:Button
    lateinit var btnlocationaddressf:Button


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        btnlocation=findViewById(R.id.btn_location)
        btnlocationaddress=findViewById(R.id.btn_location_address)
        btnlocationaddressf=findViewById(R.id.btn_location_address_f)

        btnlocationaddress.setOnClickListener {
            val intent= Intent(this,location_address::class.java)
            startActivity(intent)
        }
        btnlocation.setOnClickListener {
            val intent= Intent(this,GetLocation::class.java)
            startActivity(intent)
        }

        btnlocationaddressf.setOnClickListener {
            val intent= Intent(this,GetLocation_Address::class.java)
            startActivity(intent)
        }




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}