package com.example.lircaymarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.lircaymarket.entity.Pantry
import com.example.lircaymarket.entity.Product
import com.example.lircaymarket.entity.Shoppinglist

private lateinit var nameText: EditText
private lateinit var categoryText: EditText
private lateinit var descriptionText: EditText
private lateinit var amountText: EditText
private lateinit var priceText: EditText
private lateinit var SaveProductButton : Button
private var sameproduct: Boolean = false
private var productindex: Int = 0

private var products = arrayListOf<Product>()

class ProductShoppingListRegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_shopping_list_registration)

        var shoppinglist = intent.getParcelableExtra<Shoppinglist>("shoppinglist")

        nameText = findViewById(R.id.editTextName)
        categoryText = findViewById(R.id.editTextCategory)
        descriptionText = findViewById(R.id.editTextDescription)
        amountText = findViewById(R.id.editTextAmount)
        priceText = findViewById(R.id.editTextPrice)

        SaveProductButton = findViewById(R.id.buttonSave)

        products = shoppinglist?.products!!
        nameText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                for(i in products.indices) {
                    if(products[i].productname.toString() == nameText.editableText.toString()){
                        categoryText.setText(products[i].productcategory.toString())
                        descriptionText.setText(products[i].productdescription.toString())
                        amountText.setText(products[i].productamount.toString())
                        priceText.setText(products[i].productprice.toString())

                    }
                }
            }
        })

        SaveProductButton.setOnClickListener {

            val name = nameText.text.toString()
            val category = categoryText.text.toString()
            val description = descriptionText.text.toString()
            val amount = amountText.text.toString()
            val price  = priceText.text.toString()

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
            }else if(price == ""){

                Toast.makeText(this, R.string.empty_price_error, Toast.LENGTH_SHORT).show()
            }else if(price.toInt() <= 0) {

                Toast.makeText(this, R.string.under_0_price_error, Toast.LENGTH_SHORT).show()
            }else{
                sameproduct = false

                /*for(i in products.indices) {

                    /*if(products[i].productname == name && products[i].productcategory == category &&
                        products[i].productdescription == description){

                        sameproduct = true
                        productindex = i
                    }else{
                        sameproduct = false
                    }*/
                }
                /*if(sameproduct)
                {
                    pantry.products!![productindex].productamount = pantry.products!![productindex].productamount.plus(amount.toInt())
                    println("cantidad : ${pantry.products!![productindex].productamount}")

                    val resultIntent = Intent()
                    resultIntent.putExtra("pantry", pantry)
                    setResult(RESULT_OK, resultIntent)
                    finish()
                }*/*/
                val product = Product(products.size + 1, name, amount.toInt(), description,category,price.toInt())
                val resultIntent = Intent()
                resultIntent.putExtra("new", product)
                setResult(RESULT_OK, resultIntent)
                finish()

            }
        }

    }
}