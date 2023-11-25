package com.example.lircaymarket.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.lircaymarket.ProductRegistratationActivity
import com.example.lircaymarket.R
import com.example.lircaymarket.entity.Market
import com.example.lircaymarket.entity.Pantry
import com.example.lircaymarket.entity.Product
import com.example.lircaymarket.entity.Shoppinglist

interface ProductEditListener {
    fun onEditProduct(productid: Int)
}

class ProductPantryListAdapter(
    context: Context,
    resource: Int,
    products: List<Product>,
    val pantryId: Int,
    val editListener: ProductEditListener
) : ArrayAdapter<Product>(context, resource, products.filter { it.pantryid == pantryId }) {

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


        if (product != null && product.pantryid == pantryId) {
            nameTextView.text = product.productname
            categoryTextView.text = "Categoria: " + product.productcategory
            descriptionTextView.text = "Descripcion: " + product.productdescription
            amountTextView.text = "Cantidad: " + product.productamount.toString()
        } else {
            nameTextView.text = "Nombre:"
            categoryTextView.text = "Categoria:"
            descriptionTextView.text = "Descripcion:"
            amountTextView.text = "Cantidad:"
        }

        listItemView.setOnClickListener {
            product?.let { editListener.onEditProduct(it.productid) }
        }
        return listItemView
    }

    fun goEditProduct(productid: Int) {

    }
}


class ProductShoppingListAdapter(
    context: Context,
    resource: Int,
    products: List<Product>,
    val shoppinglistId : Int,
    val editListener: ProductEditListener
    //Cambiar pantryID
) : ArrayAdapter<Product>(context, resource, products.filter { it.shoppinglistid == shoppinglistId }) {

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


        if(product != null && product.shoppinglistid == shoppinglistId) {
            nameTextView.text = product.productname
            categoryTextView.text = "Categoria: " + product.productcategory
            descriptionTextView.text = "Descripcion: " + product.productdescription
            amountTextView.text = "Cantidad: " + product.productamount.toString()
            priceTextView.text = "Precio: $" + product.productprice.toString()
        }else{
            nameTextView.text = "Nombre:"
            categoryTextView.text = "Categoria:"
            descriptionTextView.text = "Descripcion:"
            amountTextView.text = "Cantidad:"
            priceTextView.text = "Precio: $"
        }
        listItemView.setOnClickListener {
            product?.let { editListener.onEditProduct(it.productid) }
        }
        return listItemView
    }
    fun goEditProduct(productid: Int) {

    }
}

class MarketListAdapter(
    context: Context,
    resource: Int,
    markets: List<Market>,
) : ArrayAdapter<Market>(context, resource, markets) {
    companion object{
        const val REQUEST_REGISTER = 1
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val listItemView = convertView ?: inflater.inflate(R.layout.list_item_market, null)

        // Get the patient data at the current position
        val markets = getItem(position)

        // Bind patient data to TextViews in the custom layout
        val nameTextView = listItemView.findViewById<TextView>(R.id.textViewName)
        val typeTextView = listItemView.findViewById<TextView>(R.id.textViewType)
        val directionTextView = listItemView.findViewById<TextView>(R.id.textViewDirection)


        if(markets != null) {
            nameTextView.text = markets.marketname
            typeTextView.text = "Tipo: " + markets.markettype
            directionTextView.text = "Direccion: " + markets.marketdirection
        }else{
            nameTextView.text = "Nombre:"
            typeTextView.text = "Tipo:"
            directionTextView.text = "Direccion:"
        }

        return listItemView
    }
}
