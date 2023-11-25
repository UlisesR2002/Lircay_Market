package com.example.lircaymarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.text.Editable
import android.widget.Toast
import com.example.lircaymarket.entity.SaveData
import com.example.lircaymarket.entity.Pantry
import com.example.lircaymarket.entity.Product


class ProductRegistratationActivity : AppCompatActivity() {

    private lateinit var nameText: EditText
    private lateinit var categoryText: EditText
    private lateinit var descriptionText: EditText
    private lateinit var amountText: EditText
    private lateinit var SaveProductButton : Button
    private lateinit var CancelProductButton : Button

    private var editProduct: Pantry? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_registratation)

        nameText = findViewById(R.id.editTextName)
        categoryText = findViewById(R.id.editTextCategory)
        descriptionText = findViewById(R.id.editTextDescription)
        amountText = findViewById(R.id.editTextAmount)
        SaveProductButton = findViewById(R.id.buttonSave)
        CancelProductButton = findViewById(R.id.buttonCancel)

        val index = intent.getIntExtra("index",-1)

        if(index >= 0)
        {
            editProduct = SaveData.pantry[index]
        }

        if(editProduct == null) {
            nameText.addTextChangedListener(object : TextWatcher {
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
                    for (i in SaveData.pantry.indices) {

                        /*if (SaveData.pantry[i].product?.productname.toString() == nameText.editableText.toString()) {
                            categoryText.setText(SaveData.pantry[i].product?.productcategory.toString())
                            descriptionText.setText(SaveData.pantry[i].product?.productdescription.toString())
                            amountText.setText(SaveData.pantry[i].product?.productamount.toString())
                        }*/
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
            /*nameText.setText(editProduct?.product?.productname)
            categoryText.setText(editProduct?.product?.productcategory.toString())
            descriptionText.setText(editProduct?.product?.productdescription.toString())
            amountText.setText(editProduct?.product?.productamount.toString())
            */
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
            /*
            SaveData.pantry.add(
                Pantry(
                    SaveData.pantry[1].pantryid,
                    Product(
                        SaveData.pantry.size + 1,
                        name,
                        amount.toInt(),
                        description,
                        category,
                        0
                    )
                )
            )
            */
            finish()
        }
    }

    fun onEditProduct(){
        val name = nameText.text.toString()
        val category = categoryText.text.toString()
        val description = descriptionText.text.toString()
        val amount = amountText.text.toString()

        //editProduct?.product?.productname = name
        //editProduct?.product?.productcategory = category
        //editProduct?.product?.productdescription = description
        //editProduct?.product?.productamount = amount.toInt()

        finish()
    }

    fun onDeleteProduct(){
        SaveData.pantry.remove(editProduct)
        finish()
    }
}