package com.example.lircaymarket

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.lircaymarket.adapters.ProductPantryListAdapter
import com.example.lircaymarket.entity.SaveData
import com.example.lircaymarket.entity.User

class PantryListActivity : AppCompatActivity() {

    private lateinit var listViewProducts: ListView
    private var userid: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantry_list)

        userid = intent.getIntExtra("userid",0)

        listViewProducts = findViewById(R.id.listViewProducts)

        listViewProducts.adapter = ProductPantryListAdapter(this, android.R.layout.simple_list_item_1, SaveData.pantry)

        listViewProducts.setOnItemClickListener { _, _, position, _ ->
            goEditProduct(position)
        }


    }
    override fun onResume() {
        super.onResume()
        val listView = findViewById<ListView>(R.id.listViewProducts)
        listView.adapter = ProductPantryListAdapter(this, R.layout.list_item_pantry_product, SaveData.pantry)
    }
    fun goCreateProduct(view: View) {
        val intent = Intent(this, ProductRegistratationActivity::class.java)
        intent.putExtra("userid",userid)
        startActivity(intent)
    }

    fun goEditProduct(index: Int){
        val intent = Intent(this, ProductRegistratationActivity::class.java)
        intent.putExtra("index",index)
        startActivity(intent)
    }


}