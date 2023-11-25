package com.example.lircaymarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.lircaymarket.entity.SaveData
import com.example.lircaymarket.entity.Pantry
import com.example.lircaymarket.entity.Product
import com.example.lircaymarket.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ProductRegistratationActivity : AppCompatActivity() {

    private var userid: Int = 0
    private lateinit var user: User
    private lateinit var pantry: Pantry
    private lateinit var nameText: EditText
    private lateinit var categoryText: EditText
    private lateinit var descriptionText: EditText
    private lateinit var amountText: EditText
    private lateinit var SaveProductButton : Button
    private lateinit var CancelProductButton : Button

    private var editProduct: Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_registratation)


        userid = intent.getIntExtra("userid",0)


        nameText = findViewById(R.id.editTextName)
        categoryText = findViewById(R.id.editTextCategory)
        descriptionText = findViewById(R.id.editTextDescription)
        amountText = findViewById(R.id.editTextAmount)
        SaveProductButton = findViewById(R.id.buttonSave)
        CancelProductButton = findViewById(R.id.buttonCancel)

        val index = intent.getIntExtra("productid",-1)

        if(index >= 0)
        {
            GlobalScope.launch(Dispatchers.IO) {

                editProduct = SaveData.productDao.getProductByID(index)
                nameText.setText(editProduct!!.productname)
                categoryText.setText(editProduct!!.productcategory)
                descriptionText.setText(editProduct!!.productdescription)
                amountText.setText(editProduct!!.productamount.toString())

                SaveProductButton.setOnClickListener {
                    onEditProduct()
                }
                CancelProductButton.setText(R.string.borrar_button)
                CancelProductButton.setOnClickListener {
                    onDeleteProduct()
                }
            }
        }
        else {
            SaveProductButton.setOnClickListener {
                onCreateProduct()
            }

            CancelProductButton.setOnClickListener{
                finish()
            }
        }
    }

    override fun onContentChanged() {
        super.onContentChanged()
    }

    fun onCreateProduct(){
        val appDatabase = SaveData.getDatabase(applicationContext)
        val name = nameText.text.toString()
        val category = categoryText.text.toString()
        val description = descriptionText.text.toString()
        val amount = amountText.text.toString()

        if(name == "")
        {
            Toast.makeText(this, R.string.empty_username_error, Toast.LENGTH_SHORT).show()
        }else if(category == ""){

            Toast.makeText(this, R.string.empty_category_error, Toast.LENGTH_SHORT).show()
        }else if(description == ""){

            Toast.makeText(this, R.string.empty_description_error, Toast.LENGTH_SHORT).show()
        }else if(amount == ""){

            Toast.makeText(this, R.string.empty_amount_error, Toast.LENGTH_SHORT).show()
        }else if(amount.toInt() <= 0){

            Toast.makeText(this, R.string.under_0_amount_error, Toast.LENGTH_SHORT).show()
        }else {
            GlobalScope.launch(Dispatchers.IO) {
                val products = appDatabase.productDao().getAll()
                pantry = appDatabase.pantryDao().getPantryByUserID(userid)!!
                val product = Product(products.size + 1, name, amount.toInt(), description, category, 0,pantry.pantryid)
                appDatabase.productDao().insertAll(product)
                println(product)
            }
            finish()
        }
    }

    fun onEditProduct(){
        val appDatabase = SaveData.getDatabase(applicationContext)
        val name = nameText.text.toString()
        val category = categoryText.text.toString()
        val description = descriptionText.text.toString()
        val amount = amountText.text.toString()

        GlobalScope.launch(Dispatchers.IO) {

            editProduct?.let { product ->
                val updatedProduct = product.copy(
                    productname = name,
                    productcategory = category,
                    productdescription = description,
                    productamount = amount.toInt()
                )

                appDatabase.productDao().update(updatedProduct)
            }
        }

        finish()
    }

    fun onDeleteProduct(){
        val appDatabase = SaveData.getDatabase(applicationContext)
        GlobalScope.launch(Dispatchers.IO) {
            editProduct?.let { appDatabase.productDao().delete(it) }
        }
        finish()
    }
}