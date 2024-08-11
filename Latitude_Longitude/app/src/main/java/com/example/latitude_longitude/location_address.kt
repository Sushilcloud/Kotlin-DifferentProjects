package com.example.latitude_longitude

import android.Manifest
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.location.LocationRequest
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.location.LocationManagerCompat.isLocationEnabled
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import java.util.Locale

class location_address : AppCompatActivity() {
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    lateinit var Locationtxt:TextView
    var PERMISSION_ID=5
    lateinit var getPos: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_location_address)

        Locationtxt=findViewById(R.id.Locationtxt)
        getPos=findViewById(R.id.getPos)


        // now lets initiate the fused location provider client
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        getPos.setOnClickListener {
            getLastLocation()
        }


       // the permission id is just an int that must be unique so you can use any number
       }

    // now we will create a function that will allow us to get the last location


    private fun getLastLocation(){
        // first we will check if the permission is granted

        if(CheckPermission()){
            // if the permission is granted we will check if the location service is enabled

            if(isLocationEnabled()){
                //now lets get the location
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return
                }
                fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                    val location: Location? = task.result
                    if (location == null) {



                        // Handle the case where location is null, maybe request a new location update
                    } else {
                        Locationtxt.text = "Your current coordinates are:\n" +location.latitude+ location.longitude+
                        "\nYour City"+getCityName(location.latitude,location.longitude)+"Your Country"+
                                getCountryName(location.latitude,location.longitude) +
                                "Your Pin Code"+getPostalCode(location.latitude,location.longitude)+
                                "Your State"+getSubArea(location.latitude,location.longitude)+
                                "Your Admin Area"+adminArea(location.latitude,location.longitude)


                    }
                }
            } else
                Toast.makeText(this, "Please turn on location", Toast.LENGTH_SHORT).show()

        }
        else{
        RequestPermission()
        }
    }



    // first we need to create function that will check the uses permission
    private fun CheckPermission(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) ==
            PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {

            return true
        }
        return false

    }

    // now we need to create a funtion that will allow us to get user permission
    private fun RequestPermission()
    {
        ActivityCompat.requestPermissions(this,
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,android.Manifest.permission.ACCESS_COARSE_LOCATION),PERMISSION_ID)

    }


    // We need a function that check if the location service of the device is enabled
    private fun isLocationEnabled():Boolean
    {
        var locationManager :LocationManager =getSystemService(LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode==PERMISSION_ID)
            if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
               Log.d("Debug:","You have the Permission")
            }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }



    private val locationCallback=object:LocationCallback(){
        override fun onLocationResult(p0: LocationResult) {
            var lastLocation: Location? =p0.lastLocation

            Locationtxt.text="Your current coordinates are: Lat:lastLocation.latitude; Long:lastLocation.longitude"

    }
    }


    private fun getCityName(lat:Double,long:Double): String{

        var cityName=""
        val geoCoder= Geocoder(this, Locale.getDefault())
        val address : MutableList<Address>? =geoCoder.getFromLocation(lat,long,1)
        if (address != null) {
            cityName=address.get(0).locality
        }
        return cityName
    }

    private fun getCountryName(lat:Double,long:Double): String{
        var CountryName=""
        val geoCoder= Geocoder(this, Locale.getDefault())
        var address: MutableList<Address>? =geoCoder.getFromLocation(lat,long,1)
        if (address != null) {
            CountryName=address.get(0).countryName
        }
        return CountryName

    }
    private fun getPostalCode(lat: Double, long: Double):String{

        var stateName=""
        var geoCoder = Geocoder(this, Locale.getDefault())
        var address=geoCoder.getFromLocation(lat,long,1)
        if (address != null) {
            stateName=address.get(0).postalCode
        }
        return stateName

    }

    private fun getSubArea(lat: Double, long: Double):String{

        var stateName=""
        var geoCoder = Geocoder(this, Locale.getDefault())
        var address=geoCoder.getFromLocation(lat,long,1)
        if (address != null) {
            stateName=address.get(0).subLocality
        }
        return stateName

    }

    private fun adminArea(lat: Double, long: Double):String{

        var stateName=""
        var geoCoder = Geocoder(this, Locale.getDefault())
        var address=geoCoder.getFromLocation(lat,long,1)
        if (address != null) {
            stateName=address.get(0).adminArea
        }
        return stateName

    }






}