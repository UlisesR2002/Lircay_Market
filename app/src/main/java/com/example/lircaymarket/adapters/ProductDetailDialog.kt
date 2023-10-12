package com.example.lircaymarket.adapters

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.lircaymarket.R
import com.example.lircaymarket.entity.Product

class ProductPantryDetailDialog(
    context: Context,
    private val product: Product
) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_product_detail)

        val textViewName = findViewById<TextView>(R.id.NameText)
        val textViewCategory = findViewById<TextView>(R.id.CategoryText)
        val textViewDescription = findViewById<TextView>(R.id.DescriptionText)
        val textViewAmount = findViewById<TextView>(R.id.AmountText)
        val textViewPrice = findViewById<TextView>(R.id.PriceText)
        val buttonGoBack = findViewById<Button>(R.id.buttonGoBackDialog)

        // Set patient information in TextViews
        textViewName.text = product.productname
        textViewCategory.text = product.productcategory
        textViewDescription.text = product.productdescription
        textViewAmount.text = product.productamount.toString()
        textViewPrice.text = product.productprice.toString()

        // Set a click listener for the "Go Back" button to dismiss the dialog
        buttonGoBack.setOnClickListener {
            dismiss()
        }
    }
}
