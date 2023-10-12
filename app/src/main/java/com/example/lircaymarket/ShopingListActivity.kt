package com.example.lircaymarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.example.lircaymarket.adapters.ProductPantryDetailDialog
import com.example.lircaymarket.adapters.ProductShoppingListAdapter
import com.example.lircaymarket.entity.Product
import com.example.lircaymarket.entity.Shoppinglist

class ShopingListActivity : AppCompatActivity() {

    private lateinit var  listViewProducts: ListView
    private lateinit var totalprice: TextView
    private var listOption: Boolean = true
    private var detailOption: Boolean = false
    private lateinit var shoppinglist: Shoppinglist
    private var products = arrayListOf<Product>()
    private lateinit var adapterItems: ProductShoppingListAdapter
    private lateinit var adapter : ArrayAdapter<Product>

    companion object{
        const val REQUEST_REGISTER = 1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shoping_list)

        shoppinglist = intent.getParcelableExtra<Shoppinglist>("shoppinglist")!!

        listViewProducts = findViewById(R.id.listViewProducts)

        if(shoppinglist.shoppinglistid == 1) {
            shoppinglist.products?.add(
                Product(
                    shoppinglist.products!!.size + 1,
                    "Leche",
                    1,
                    "Caja de un litro de leche de marca colun",
                    "Alimento",
                    1000
                )
            )

            shoppinglist.products?.add(
                Product(
                    shoppinglist.products!!.size + 1,
                    "Hamburguesa",
                    5,
                    "Paquete de hamburguesa individual de marca pf",
                    "Alimento",
                    200
                )
            )
        }
        products = shoppinglist?.products!!

        for(i in products) {

           //shoppinglist.totalprice = products[i].productamount * products[i].productprice

        }
        shoppinglist.totalprice = 1
        totalprice = findViewById(R.id.TotalpriceText)



        totalprice.setText("Precio total:\n$"+shoppinglist.totalprice.toString())



        adapter = ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1, shoppinglist.products!!)

        listViewProducts.adapter = adapter

        changeAdapter()

        listViewProducts.setOnItemClickListener { _, _, position, _ ->
            val selectedPatient = products[position]
            listOption = !listOption
            if (detailOption) {
                showProductDetailDialog(selectedPatient)
            }
            else {
                val intent = Intent(this, ProductDetailActivity::class.java)
                intent.putExtra("product", selectedPatient)
                startActivity(intent)
            }
        }
    }
    fun changeAdapter() {
        if (listOption) {
            adapterItems = ProductShoppingListAdapter(this, R.layout.list_item_pantry_product, shoppinglist.products!!)
            listViewProducts.adapter = adapterItems
        } else {
            // Perform actions when listOption is false
            //adapter = ArrayAdapter<Patient>(this, android.R.layout.simple_list_item_1, patients.map { it.name })
            adapter = ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1, shoppinglist.products!!)
            listViewProducts.adapter = adapter
        }
        listOption = !listOption
    }

    private fun showProductDetailDialog(product: Product) {
        val dialog = ProductPantryDetailDialog(this, product)
        dialog.show()
    }

    fun goCreateProduct(view: View)
    {
        val intent = Intent(this, ProductShoppingListRegistrationActivity::class.java)
        intent.putExtra("shoppinglist", shoppinglist)
        startActivityForResult(intent, REQUEST_REGISTER)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == LoginActivity.REQUEST_REGISTER && resultCode == RESULT_OK) {
            val newProduct = data?.getParcelableExtra<Product>("new")
            if(newProduct != null){
                shoppinglist.products?.add(newProduct)

                if(listOption){

                }
                adapterItems.notifyDataSetChanged()

            }
        }
    }
}