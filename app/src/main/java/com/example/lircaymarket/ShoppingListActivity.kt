package com.example.lircaymarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.TextView
import com.example.lircaymarket.adapters.ProductShoppingListAdapter
import com.example.lircaymarket.entity.SaveData

class ShoppingListActivity : AppCompatActivity() {

    private lateinit var  listViewProducts: ListView
    private lateinit var  totatpricetext: TextView
    private var totalprice: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shoping_list)

        listViewProducts = findViewById(R.id.listViewProducts)

        listViewProducts.adapter = ProductShoppingListAdapter(this, android.R.layout.simple_list_item_1, SaveData.shoppinglist)

        totatpricetext = findViewById(R.id.totalpriceText)

        listViewProducts.setOnItemClickListener { _, _, position, _ ->
            goEditProduct(position)
        }


    }

    override fun onResume() {
        super.onResume()

        totalprice = 0
        val listView = findViewById<ListView>(R.id.listViewProducts)
        listView.adapter = ProductShoppingListAdapter(this, R.layout.list_item_shoppinglist_product, SaveData.shoppinglist)

        for (i in SaveData.shoppinglist.indices) {
            totalprice += SaveData.shoppinglist[i].product?.productprice!!.toInt() * SaveData.shoppinglist[i].product?.productamount!!.toInt()
        }

        totatpricetext.setText("Precio total: \n$" + totalprice)


    }

    fun goCreateProduct(view: View)
    {
        println("Create")
        val intent = Intent(this, ProductShoppingListRegistrationActivity::class.java)
        startActivity(intent)
    }

    fun goEditProduct(index: Int){
        val intent = Intent(this, ProductShoppingListRegistrationActivity::class.java)
        intent.putExtra("index",index)
        startActivity(intent)
    }
}