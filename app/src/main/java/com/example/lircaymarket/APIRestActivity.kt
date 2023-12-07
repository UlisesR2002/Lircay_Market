package com.example.lircaymarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.lircaymarket.api.ApiCallback
import com.example.lircaymarket.api.ApiTask

class APIRestActivity : AppCompatActivity(), ApiCallback {
private lateinit var apiTask: ApiTask
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apirest)
        apiTask = ApiTask(this)
        var button = findViewById<Button>(R.id.button)

        button.setOnClickListener{
            apiTask.execute("https://zelda.fanapis.com/api/games?limit=2")
        }
    }

    override fun onRequestComplete(result: String) {
        var text = findViewById<TextView>(R.id.ApiTextView)

        text.text = result;
    }
}