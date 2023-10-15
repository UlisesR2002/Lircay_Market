package com.example.lircaymarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.TextView
import com.example.lircaymarket.adapters.ProductShoppingListAdapter
import com.example.lircaymarket.entity.DataManager

class ShopingListActivity : AppCompatActivity() {

    private lateinit var  listViewProducts: ListView
    private lateinit var  totatpricetext: TextView
    private var totalprice: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shoping_list)

        listViewProducts = findViewById(R.id.listViewProducts)

        listViewProducts.adapter = ProductShoppingListAdapter(this, android.R.layout.simple_list_item_1, DataManager.shoppinglist)

        listViewProducts.setOnItemClickListener { _, _, position, _ ->
            goEditProduct(position)
        }

        totatpricetext = findViewById(R.id.totalpriceText)
    }

    override fun onResume() {
        super.onResume()

        totalprice = 0
        val listView = findViewById<ListView>(R.id.listViewProducts)
        listView.adapter = ProductShoppingListAdapter(this, R.layout.list_item_shoppinglist_product, DataManager.shoppinglist)

        for (i in DataManager.shoppinglist.indices) {
            totalprice += DataManager.shoppinglist[i].product?.productprice!!.toInt() * DataManager.shoppinglist[i].product?.productamount!!.toInt()
        }


        totatpricetext.setText("Precio total: \n$" + totalprice.toString())
    }

    fun goCreateProduct(view: View)
    {
        val intent = Intent(this, ProductShoppingListRegistrationActivity::class.java)
        startActivity(intent)
    }

    fun goEditProduct(index: Int){
        val intent = Intent(this, ProductShoppingListRegistrationActivity::class.java)
        intent.putExtra("index",index)
        startActivity(intent)
    }
}