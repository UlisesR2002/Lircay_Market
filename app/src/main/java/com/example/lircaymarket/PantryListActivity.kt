package com.example.lircaymarket

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.lircaymarket.adapters.ProductPantryListAdapter
import com.example.lircaymarket.entity.DataManager

class PantryListActivity : AppCompatActivity() {

    private lateinit var listViewProducts: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantry_list)

        listViewProducts = findViewById(R.id.listViewProducts)

        listViewProducts.adapter = ProductPantryListAdapter(this, android.R.layout.simple_list_item_1, DataManager.pantry)

        listViewProducts.setOnItemClickListener { _, _, position, _ ->
            goEditProduct(position)
        }


    }
    override fun onResume() {
        super.onResume()
        val listView = findViewById<ListView>(R.id.listViewProducts)
        listView.adapter = ProductPantryListAdapter(this, R.layout.list_item_pantry_product, DataManager.pantry)
    }
    fun goCreateProduct(view: View) {
        val intent = Intent(this, ProductRegistratationActivity::class.java)
        startActivity(intent)
    }

    fun goEditProduct(index: Int){
        val intent = Intent(this, ProductRegistratationActivity::class.java)
        intent.putExtra("index",index)
        startActivity(intent)
    }


}