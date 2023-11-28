package com.example.lircaymarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.SearchView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.lircaymarket.adapters.ProductEditListener
import com.example.lircaymarket.adapters.ProductPantryListAdapter
import com.example.lircaymarket.adapters.ProductShoppingListAdapter
import com.example.lircaymarket.entity.Product
import com.example.lircaymarket.entity.SaveData
import com.example.lircaymarket.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShoppingListActivity : AppCompatActivity(), ProductEditListener {

    private lateinit var  totatpricetext: TextView
    private lateinit var  listViewProducts: ListView
    private var totalprice: Int = 0
    private lateinit var products: List<Product>
    private var userid: Int = 0
    private var shoppinglistid: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shoping_list)

        totatpricetext = findViewById(R.id.totalpriceText)
        userid = intent.getIntExtra("userid",0)

        lifecycleScope.launch {
            val shoppinglist = withContext(Dispatchers.IO) {
                SaveData.getDatabase(applicationContext).shoppinglistDao().getShoppinglistByUserID(userid)
            }

            shoppinglistid = shoppinglist!!.shoppinglistid
            products = withContext(Dispatchers.IO) {
                SaveData.getDatabase(applicationContext).productDao().getAll()
            }
            setupUI()
        }


    }
    private fun setupUI() {
        listViewProducts = findViewById(R.id.listViewProducts)

        listViewProducts.adapter = ProductShoppingListAdapter(
            applicationContext,
            android.R.layout.simple_list_item_1,
            products,
            shoppinglistid,
            this // Pasa la instancia de la actividad como listener
        )
        listViewProducts.setOnItemClickListener { _, _, position, _ ->
            val selectedProduct = (listViewProducts.adapter as ProductShoppingListAdapter).getItem(position)
            selectedProduct?.let {
                onEditProduct(it.productid)
                finish()
            }
        }

        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                (listViewProducts.adapter as ProductShoppingListAdapter).filter.filter(newText)
                return false
            }
        })
    }
    override fun onResume() {
        super.onResume()

        totalprice = 0
        lifecycleScope.launch {
            val shoppinglist = withContext(Dispatchers.IO) {
                SaveData.getDatabase(applicationContext).shoppinglistDao().getShoppinglistByUserID(userid)
            }

            shoppinglistid = shoppinglist!!.shoppinglistid

            // Obtener la lista de productos en otro hilo de fondo
            products = withContext(Dispatchers.IO) {
                SaveData.getDatabase(applicationContext).productDao().getProductsByShoppingListId(shoppinglistid)
            }

            // Configurar la UI después de obtener los datos.
            setupUI()

            // Calcula el precio
            totalprice = products.sumBy { it.productprice * it.productamount }
            totatpricetext.text = "${resources.getString(R.string.total_price_text)} ${totalprice}"
        }
    }

    fun goCreateProduct(view: View)
    {
        val intent = Intent(this, ProductShoppingListRegistrationActivity::class.java)
        intent.putExtra("userid",userid)
        startActivity(intent)
    }

    override fun onEditProduct(productid: Int){
        val intent = Intent(this, ProductShoppingListRegistrationActivity::class.java)
        intent.putExtra("userid",userid)
        intent.putExtra("productid", productid)
        startActivity(intent)
    }
}
