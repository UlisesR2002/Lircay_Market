package com.example.lircaymarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.lircaymarket.entity.Movement
import com.example.lircaymarket.entity.SaveData
import com.example.lircaymarket.entity.Product
import com.example.lircaymarket.entity.Shoppinglist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ProductShoppingListRegistrationActivity : AppCompatActivity() {

    private var userid: Int = 0
    private lateinit var shoppinglist: Shoppinglist
    private lateinit var nameText: EditText
    private lateinit var categoryText: EditText
    private lateinit var descriptionText: EditText
    private lateinit var amountText: EditText
    private lateinit var priceText: EditText
    private lateinit var SaveProductButton : Button
    private lateinit var CancelProductButton : Button

    private var editProduct: Product? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_shopping_list_registration)

        userid = intent.getIntExtra("userid",0)

        nameText = findViewById(R.id.editTextName)
        categoryText = findViewById(R.id.editTextCategory)
        descriptionText = findViewById(R.id.editTextDescription)
        amountText = findViewById(R.id.editTextAmount)
        priceText = findViewById(R.id.editTextPrice)
        SaveProductButton = findViewById(R.id.buttonSave)
        CancelProductButton = findViewById(R.id.buttonCancel2)

        val index = intent.getIntExtra("productid",-1)

        if(index >= 0)
        {
            GlobalScope.launch(Dispatchers.IO) {

                editProduct = SaveData.productDao.getProductByID(index)
                nameText.setText(editProduct!!.productname)
                categoryText.setText(editProduct!!.productcategory.toString())
                descriptionText.setText(editProduct!!.productdescription.toString())
                amountText.setText(editProduct!!.productamount.toString())
                priceText.setText(editProduct!!.productprice.toString())

                SaveProductButton.setOnClickListener {
                    onEditProduct()
                }
                CancelProductButton.setText(R.string.borrar_button)
                CancelProductButton.setOnClickListener {
                    onDeleteProduct()
                }
            }
        }else{
            SaveProductButton.setOnClickListener {
                onCreateProduct()
            }

            CancelProductButton.setOnClickListener {
                finish()
            }
        }
    }

    fun onCreateProduct(){
        val appDatabase = SaveData.getDatabase(applicationContext)
        val name = nameText.text.toString()
        val category = categoryText.text.toString()
        val description = descriptionText.text.toString()
        val amount = amountText.text.toString()
        val price = priceText.text.toString()

        if (name == "") {
            Toast.makeText(this, R.string.empty_username_error, Toast.LENGTH_SHORT).show()
        } else if (category == "") {

            Toast.makeText(this, R.string.empty_category_error, Toast.LENGTH_SHORT).show()
        } else if (description == "") {

            Toast.makeText(this, R.string.empty_description_error, Toast.LENGTH_SHORT)
                .show()
        } else if (amount == "") {

            Toast.makeText(this, R.string.empty_amount_error, Toast.LENGTH_SHORT).show()
        } else if (amount.toInt() <= 0) {

            Toast.makeText(this, R.string.under_0_amount_error, Toast.LENGTH_SHORT).show()
        } else if (price == "") {

            Toast.makeText(this, R.string.empty_price_error, Toast.LENGTH_SHORT).show()
        } else if (price.toInt() <= 0) {

            Toast.makeText(this, R.string.under_0_price_error, Toast.LENGTH_SHORT).show()
        }else {
            GlobalScope.launch(Dispatchers.IO) {
                val products = appDatabase.productDao().getAll()
                val movements = appDatabase.movementDao().getAll()
                shoppinglist = appDatabase.shoppinglistDao().getShoppinglistByUserID(userid)!!
                val product = Product(products.size + 1, name, amount.toInt(), description, category, price.toInt(),null, shoppinglist.shoppinglistid)
                val movement = Movement(movements.size + 1, userid, product.productname, 4)
                appDatabase.productDao().insertAll(product)
                appDatabase.movementDao().insertAll(movement)
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
        val price = priceText.text.toString()

        GlobalScope.launch(Dispatchers.IO) {

            editProduct?.let { product ->
                val updatedProduct = product.copy(
                    productname = name,
                    productcategory = category,
                    productdescription = description,
                    productamount = amount.toInt(),
                    productprice = price.toInt()
                )

                appDatabase.productDao().update(updatedProduct)
            }
            val movements = appDatabase.movementDao().getAll()
            val movement = Movement(movements.size + 1, userid, editProduct?.productname, 5)
            appDatabase.movementDao().insertAll(movement)
        }

        finish()
    }

    fun onDeleteProduct(){
        val appDatabase = SaveData.getDatabase(applicationContext)
        GlobalScope.launch(Dispatchers.IO) {
            val movements = appDatabase.movementDao().getAll()
            val movement = Movement(movements.size + 1, userid, editProduct?.productname, 6)
            appDatabase.movementDao().insertAll(movement)
            editProduct?.let { appDatabase.productDao().delete(it) }
        }
        finish()
    }
}