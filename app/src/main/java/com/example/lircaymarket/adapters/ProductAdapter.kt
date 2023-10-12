package com.example.lircaymarket.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.lircaymarket.R
import com.example.lircaymarket.entity.Product

class ProductPantryListAdapter(
    context: Context,
    resource: Int,
    products: List<Product>,
) : ArrayAdapter<Product>(context, resource, products) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val listItemView = convertView ?: inflater.inflate(R.layout.list_item_pantry_product, null)

        // Get the patient data at the current position
        val product = getItem(position)

        // Bind patient data to TextViews in the custom layout
        val nameTextView = listItemView.findViewById<TextView>(R.id.textViewName)
        val categoryTextView = listItemView.findViewById<TextView>(R.id.textViewCategory)
        val descriptionTextView = listItemView.findViewById<TextView>(R.id.textViewDescription)
        val amountTextView = listItemView.findViewById<TextView>(R.id.textViewAmount)


        // Set the patient data in the TextViews
        nameTextView.text = product?.productname
        categoryTextView.text = "Categoria: " + product?.productcategory
        descriptionTextView.text = "Descripcion: " + product?.productdescription
        amountTextView.text = "Cantidad: " + product?.productamount.toString()

        return listItemView
    }
}

class ProductShoppingListAdapter(
    context: Context,
    resource: Int,
    products: List<Product>,
) : ArrayAdapter<Product>(context, resource, products) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val listItemView = convertView ?: inflater.inflate(R.layout.list_item_shoppinglist_product, null)

        // Get the patient data at the current position
        val product = getItem(position)

        // Bind patient data to TextViews in the custom layout
        val nameTextView = listItemView.findViewById<TextView>(R.id.textViewName)
        val categoryTextView = listItemView.findViewById<TextView>(R.id.textViewCategory)
        val descriptionTextView = listItemView.findViewById<TextView>(R.id.textViewDescription)
        val amountTextView = listItemView.findViewById<TextView>(R.id.textViewAmount)
        val priceTextView = listItemView.findViewById<TextView>(R.id.textViewPrice)


        // Set the patient data in the TextViews
        nameTextView.text = product?.productname
        categoryTextView.text = "Categoria: " + product?.productcategory
        descriptionTextView.text = "Descripcion: " + product?.productdescription
        amountTextView.text = "Cantidad: " + product?.productamount.toString()
        priceTextView.text = "Precio: $" + product?.productprice.toString()

        return listItemView
    }
}
