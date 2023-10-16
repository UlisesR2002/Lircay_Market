package com.example.lircaymarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.lircaymarket.entity.SaveData
import com.example.lircaymarket.entity.Product
import com.example.lircaymarket.entity.Shoppinglist




class ProductShoppingListRegistrationActivity : AppCompatActivity() {
    private lateinit var SnameText: EditText
    private lateinit var categoryText: EditText
    private lateinit var descriptionText: EditText
    private lateinit var amountText: EditText
    private lateinit var priceText: EditText
    private lateinit var SaveProductButton : Button
    private lateinit var CancelProductButton : Button

    private var editProduct: Shoppinglist? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_shopping_list_registration)

        SnameText = findViewById(R.id.editTextName)
        categoryText = findViewById(R.id.editTextCategory)
        descriptionText = findViewById(R.id.editTextDescription)
        amountText = findViewById(R.id.editTextAmount)
        priceText = findViewById(R.id.editTextPrice)
        SaveProductButton = findViewById(R.id.buttonSave)
        CancelProductButton = findViewById(R.id.buttonCancel2)

        val index = intent.getIntExtra("index",-1)

        if(index >= 0)
        {
            editProduct = SaveData.shoppinglist[index]
        }

        if(editProduct == null) {
            SnameText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    for (i in SaveData.shoppinglist.indices) {

                        if (SaveData.shoppinglist[i].product?.productname.toString() == SnameText.editableText.toString()) {
                            categoryText.setText(SaveData.shoppinglist[i].product?.productcategory.toString())
                            descriptionText.setText(SaveData.shoppinglist[i].product?.productdescription.toString())
                            amountText.setText(SaveData.shoppinglist[i].product?.productamount.toString())
                            priceText.setText(SaveData.shoppinglist[i].product?.productprice.toString())

                        }
                    }
                }
            })

            SaveProductButton.setOnClickListener {
                onCreateProduct()
            }

            CancelProductButton.setOnClickListener{
                finish()
            }
        }else{
            SnameText.setText(editProduct?.product?.productname)
            categoryText.setText(editProduct?.product?.productcategory.toString())
            descriptionText.setText(editProduct?.product?.productdescription.toString())
            amountText.setText(editProduct?.product?.productamount.toString())
            priceText.setText(editProduct?.product?.productprice.toString())
            SaveProductButton.setOnClickListener {
                onEditProduct()
            }
            CancelProductButton.setText(R.string.borrar_button)
            CancelProductButton.setOnClickListener {
                onDeleteProduct()
            }
        }
    }
    override fun onContentChanged() {
        super.onContentChanged()
    }

    fun onCreateProduct(){

        val name = SnameText.text.toString()
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

            SaveData.shoppinglist.add(
                Shoppinglist(
                    SaveData.shoppinglist[1].shoppinglistid,
                    Product(
                        SaveData.pantry.size + 1,
                        name,
                        amount.toInt(),
                        description,
                        category,
                        price.toInt()

                    )
                )
            )
            finish()
        }
    }
    fun onEditProduct(){
        val name = SnameText.text.toString()
        val category = categoryText.text.toString()
        val description = descriptionText.text.toString()
        val amount = amountText.text.toString()
        val price = priceText.text.toString()

        editProduct?.product?.productname = name
        editProduct?.product?.productcategory = category
        editProduct?.product?.productdescription = description
        editProduct?.product?.productamount = amount.toInt()
        editProduct?.product?.productprice = price.toInt()

        finish()
    }

    fun onDeleteProduct(){
        SaveData.shoppinglist.remove(editProduct)
        finish()
    }
}