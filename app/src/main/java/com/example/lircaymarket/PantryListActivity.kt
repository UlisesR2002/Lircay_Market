package com.example.lircaymarket

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.lircaymarket.adapters.ProductEditListener
import com.example.lircaymarket.adapters.ProductPantryListAdapter
import com.example.lircaymarket.entity.Product
import com.example.lircaymarket.entity.SaveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PantryListActivity : AppCompatActivity(), ProductEditListener {

    private lateinit var listViewProducts: ListView
    private lateinit var products: List<Product>
    private var userid: Int = 0
    private var pantryid: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantry_list)

        userid = intent.getIntExtra("userid",0)

        lifecycleScope.launch {
            val pantry = withContext(Dispatchers.IO) {
                SaveData.getDatabase(applicationContext).pantryDao().getPantryByUserID(userid)
            }

            pantryid = pantry!!.pantryid
            products = withContext(Dispatchers.IO) {
                SaveData.getDatabase(applicationContext).productDao().getAll()
            }
            setupUI()
        }
    }
    private fun setupUI() {
        listViewProducts = findViewById(R.id.listViewProducts)

        listViewProducts.adapter = ProductPantryListAdapter(
            applicationContext,
            android.R.layout.simple_list_item_1,
            products,
            pantryid,
            this  // Pasa la instancia de la actividad como listener
        )

        listViewProducts.setOnItemClickListener { _, _, position, _ ->
            val selectedProduct = (listViewProducts.adapter as ProductPantryListAdapter).getItem(position)
            selectedProduct?.let {
                onEditProduct(it.productid)
                finish()
            }

        }
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            val pantry = withContext(Dispatchers.IO) {
                SaveData.getDatabase(applicationContext).pantryDao().getPantryByUserID(userid)
            }

            pantryid = pantry!!.pantryid

            // Obtener la lista de productos en otro hilo de fondo
            products = withContext(Dispatchers.IO) {
                SaveData.getDatabase(applicationContext).productDao().getAll()
            }

            // Configurar la UI después de obtener los datos.
            setupUI()
        }
    }
    fun goCreateProduct(view: View) {
        val intent = Intent(this, ProductRegistratationActivity::class.java)
        intent.putExtra("userid",userid)
        startActivity(intent)
    }

    override fun onEditProduct(productid: Int) {
        // Implementa aquí la lógica para abrir la actividad de edición con el ID del producto
        val intent = Intent(this, ProductRegistratationActivity::class.java)
        intent.putExtra("userid",userid)
        intent.putExtra("productid", productid)
        startActivity(intent)
    }
}