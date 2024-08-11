package com.example.latitude_longitude

import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.Locale

@Suppress("DEPRECATION")
class GetLocation_Address : AppCompatActivity() {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var latitude: TextView
    private lateinit var longitude: TextView
    private lateinit var address: TextView
    private lateinit var button: Button
    private lateinit var country: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_get_location_address)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        latitude = findViewById(R.id.latitude)
        longitude = findViewById(R.id.longitude)
        address = findViewById(R.id.address)
        button = findViewById(R.id.button)

        button.setOnClickListener {

            getLocation()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun getLocation() {

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                100
            )
            return
        }
        val location = fusedLocationProviderClient.lastLocation
        location.addOnSuccessListener {
            if (it != null) {
                latitude.text = "Latitude" + it.latitude.toString()
                longitude.text = "Longitude" + it.longitude.toString()
                val textAddress = "Address" + getAdressName(it.latitude, it.longitude)
                val textCountry = "Country" + getCountryName(it.latitude, it.longitude)
                address.text = textAddress
                country.text = textCountry
                country.text = textCountry


            }
        }
    }

    private fun getAdressName(lat: Double, long: Double): String {

        var addressName=""
        var geoCoder = Geocoder(this, Locale.getDefault())
        var address=geoCoder.getFromLocation(lat,long,1)
        if (address != null) {
            addressName=address.get(0).getAddressLine(0)
        }
        return addressName

    }

    private fun getCountryName(lat: Double, long: Double): String {

        var countryName=""
        var geoCoder = Geocoder(this, Locale.getDefault())
        var address=geoCoder.getFromLocation(lat,long,1)
        if (address != null) {
            countryName=address.get(0).countryName
        }
        return countryName
    }




}