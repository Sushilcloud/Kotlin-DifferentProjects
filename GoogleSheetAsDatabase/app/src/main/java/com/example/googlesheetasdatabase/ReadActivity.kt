package com.example.googlesheetasdatabase

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class ReadActivity : AppCompatActivity() {

    lateinit var readProgressBar: ProgressBar
    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: LinearLayoutManager
    lateinit var recyclerAdapter: ReadRecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_read)

        readProgressBar=findViewById(R.id.readProgressBar)
        recyclerView=findViewById(R.id.recyclerView)
        layoutManager= LinearLayoutManager(this@ReadActivity)

        val bookList=ArrayList<Book>()
        readProgressBar.visibility= View.GONE

        val queue= Volley.newRequestQueue(this@ReadActivity)
        val url="https://script.google.com/macros/s/AKfycbwhYWrdKipRP-jDoeOLAJGNQQHIhJCau6nz4f7XebHyn45nOIKDsSRbNAsTnh287fxYGA/exec"
        val jsonObjectRequest= object: JsonObjectRequest (Method.GET,url,null,
            Response.Listener {
                readProgressBar.visibility=View.GONE
            val data=it.getJSONArray("bookList")
        for(i in 0 until data.length()){
            val bookJasonObject=data.getJSONObject(i)
            val bookObject=Book(
                bookJasonObject.getString("bookName"),
                bookJasonObject.getString("bookAuthor"),
                bookJasonObject.getInt("bookPrice"),
                bookJasonObject.getString("bookRating")
            )
            bookList.add(bookObject)
        }

                recyclerAdapter=ReadRecyclerAdapter(this,bookList)
                recyclerView.adapter=recyclerAdapter
                recyclerView.layoutManager=layoutManager
        }, Response.ErrorListener {
            readProgressBar.visibility=View.GONE
            Toast.makeText(this@ReadActivity,it.toString(), Toast.LENGTH_SHORT).show()
            }


        ) {
            override fun getHeaders(): MutableMap<String, String> {
                return super.getHeaders()
            }
        }

        queue.add(jsonObjectRequest)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}