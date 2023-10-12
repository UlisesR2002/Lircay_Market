package com.example.lircaymarket

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.lircaymarket.adapters.ProductPantryListAdapter
import com.example.lircaymarket.entity.Pantry
import com.example.lircaymarket.entity.Product
import com.example.lircaymarket.entity.User

class PantryListActivity : AppCompatActivity() {

    private lateinit var  listViewProducts: ListView
    private var listOption: Boolean = true
    private var detailOption: Boolean = false
    private lateinit var user: User
    private lateinit var pantry: Pantry
    private var products = arrayListOf<Product>()
    private lateinit var adapterItems: ProductPantryListAdapter
    private lateinit var adapter : ArrayAdapter<Product>

    companion object{
        const val REQUEST_REGISTER = 1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantry_list)

        //user = intent.getParcelableExtra<User>("users")!!

        //pantry = Pantry(user.pantryid, arrayListOf<Product>())

        pantry = intent.getParcelableExtra<Pantry>("pantry")!!

        listViewProducts = findViewById(R.id.listViewProducts)

        /*if(pantry.pantryid == 1) {
            pantry.products?.add(
                Product(
                    pantry.products!!.size + 1,
                    "Ravioli carne",
                    1,
                    "Paquete de 400 gramos de pasta rellena de marca carrozi",
                    "Alimento",
                    0
                )
            )

            pantry.products?.add(
                Product(
                    pantry.products!!.size + 1,
                    "Coca-cola",
                    2,
                    "Bebida de dos litros de la marca coca-cola",
                    "Alimento",
                    0
                )
            )
        }*/
        adapter = ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1, pantry.products!!)

        listViewProducts.adapter = adapter

        changeAdapter()
    }

    fun changeAdapter() {
        if (listOption) {
            adapterItems = ProductPantryListAdapter(this, R.layout.list_item_pantry_product, pantry.products!!)
            listViewProducts.adapter = adapterItems
        } else {
            // Perform actions when listOption is false
            //adapter = ArrayAdapter<Patient>(this, android.R.layout.simple_list_item_1, patients.map { it.name })
            adapter = ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1, pantry.products!!)
            listViewProducts.adapter = adapter
        }
        listOption = !listOption
    }
    fun goCreateProduct(view: View)
    {
        val intent = Intent(this, ProductRegistratationActivity::class.java)
        intent.putExtra("pantry", pantry)
        startActivityForResult(intent, REQUEST_REGISTER)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == LoginActivity.REQUEST_REGISTER && resultCode == RESULT_OK) {
            val newProduct = data?.getParcelableExtra<Product>("new")
            if(newProduct != null){
                pantry.products?.add(newProduct)

                if(listOption){

                }
                adapterItems.notifyDataSetChanged()

            }
        }
    }
}