package com.example.googlesheetasdatabase

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class WriteActivity : AppCompatActivity() {

    lateinit var writeProgressLayout:RelativeLayout
    lateinit var writeProgressBar:ProgressBar

    lateinit var edtBookName:EditText
    lateinit var edtBookAuthor:EditText
    lateinit var edtBookPrice: EditText
    lateinit var ratingBar: RatingBar
    lateinit var btnSavetoDrive: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_write)

        writeProgressLayout=findViewById(R.id.writeProgressLayout)
        writeProgressBar=findViewById(R.id.writeProgressBar)
        edtBookName=findViewById(R.id.editBookName)
        edtBookAuthor=findViewById(R.id.editBookAuthor)
        edtBookPrice=findViewById(R.id.editBookPrice)
        ratingBar=findViewById(R.id.ratingBar)
        btnSavetoDrive=findViewById(R.id.btnSavetoDrive)

        writeProgressBar.visibility=View.GONE
        writeProgressLayout.visibility=View.GONE

        btnSavetoDrive.setOnClickListener {
                if(edtBookName.text.toString().isEmpty()
                or edtBookAuthor.text.toString().isEmpty()
                or edtBookPrice.text.toString().isEmpty()
                or ratingBar.rating.toString().isEmpty()){
                Toast.makeText(this,"Please fill all the fields",Toast.LENGTH_SHORT).show()}
            else{

                val url="https://script.google.com/macros/s/AKfycbxlDg1R1wfoWgGdyNFvCukRS92j1aQnxNN_TI-lUtLxPIza3bYSOTgAFblbAqNWEnFXJQ/exec"
                val stringRequest=object : StringRequest(Request.Method.POST,url
                    ,Response.Listener {
                    Toast.makeText(this@WriteActivity,it.toString(),Toast.LENGTH_SHORT).show()
                },
                    Response.ErrorListener {
                    Toast.makeText(this@WriteActivity,it.toString(),Toast.LENGTH_SHORT).show()
                }){
                    override fun getParams(): MutableMap<String, String> {
                        val params = HashMap<String,String>()
                        params["bookName"] =edtBookName.text.toString()
                        params["bookAuthor"] =edtBookAuthor.text.toString()
                        params["bookPrice"] =edtBookPrice.text.toString()
                        params["bookRating"] =ratingBar.rating.toString()
                        return params

                    }
                }
                val queue :RequestQueue = Volley.newRequestQueue(this@WriteActivity)
                queue.add(stringRequest)

                }
            }
        }
    }
