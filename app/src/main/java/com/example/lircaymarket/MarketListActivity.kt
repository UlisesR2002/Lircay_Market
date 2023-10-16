package com.example.lircaymarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import com.example.lircaymarket.adapters.MarketListAdapter
import com.example.lircaymarket.entity.SaveData

class MarketListActivity : AppCompatActivity() {
    private lateinit var listViewMarket: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market_list)

        listViewMarket = findViewById(R.id.listViewMarket)

        listViewMarket.adapter = MarketListAdapter(this, android.R.layout.simple_list_item_1, SaveData.Market)

        listViewMarket.setOnItemClickListener { _, _, position, _ ->
            goEditProduct(position)
        }
    }
    override fun onResume() {
        super.onResume()
        val listView = findViewById<ListView>(R.id.listViewMarket)
        listView.adapter = MarketListAdapter(this, R.layout.list_item_market, SaveData.Market)
    }
    fun goCreateMarket(view: View) {
        val intent = Intent(this, MarketRegistrationActivity::class.java)
        startActivity(intent)
    }
    fun goEditProduct(index: Int){
        val intent = Intent(this, MarketRegistrationActivity::class.java)
        intent.putExtra("index",index)
        startActivity(intent)
    }
}
