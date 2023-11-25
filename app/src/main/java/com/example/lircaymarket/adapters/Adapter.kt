package com.example.lircaymarket.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.lircaymarket.R
import com.example.lircaymarket.entity.Market
import com.example.lircaymarket.entity.Pantry
import com.example.lircaymarket.entity.Shoppinglist

class ProductPantryListAdapter(
    context: Context,
    resource: Int,
    products: List<Pantry>,
) : ArrayAdapter<Pantry>(context, resource, products) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val listItemView = convertView ?: inflater.inflate(R.layout.list_item_pantry_product, null)

        // Get the patient data at the current position
        val pantry = getItem(position)

        // Bind patient data to TextViews in the custom layout
        val nameTextView = listItemView.findViewById<TextView>(R.id.textViewName)
        val categoryTextView = listItemView.findViewById<TextView>(R.id.textViewCategory)
        val descriptionTextView = listItemView.findViewById<TextView>(R.id.textViewDescription)
        val amountTextView = listItemView.findViewById<TextView>(R.id.textViewAmount)


        /*if(pantry?.product != null) {
            nameTextView.text = pantry?.product?.productname
            categoryTextView.text = "Categoria: " + pantry?.product?.productcategory
            descriptionTextView.text = "Descripcion: " + pantry?.product?.productdescription
            amountTextView.text = "Cantidad: " + pantry?.product?.productamount.toString()
        }
        else{
            nameTextView.text = "Nombre:"
            categoryTextView.text = "Categoria:"
            descriptionTextView.text = "Descripcion:"
            amountTextView.text = "Cantidad:"
        }*/
        return listItemView
    }
}

class ProductShoppingListAdapter(
    context: Context,
    resource: Int,
    products: List<Shoppinglist>,
) : ArrayAdapter<Shoppinglist>(context, resource, products) {
    companion object{
        const val REQUEST_REGISTER = 1
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val listItemView = convertView ?: inflater.inflate(R.layout.list_item_shoppinglist_product, null)

        // Get the patient data at the current position
        val shoppinglist = getItem(position)

        // Bind patient data to TextViews in the custom layout
        val nameTextView = listItemView.findViewById<TextView>(R.id.textViewName)
        val categoryTextView = listItemView.findViewById<TextView>(R.id.textViewCategory)
        val descriptionTextView = listItemView.findViewById<TextView>(R.id.textViewDescription)
        val amountTextView = listItemView.findViewById<TextView>(R.id.textViewAmount)
        val priceTextView = listItemView.findViewById<TextView>(R.id.textViewPrice)


        if(shoppinglist?.product != null) {
            nameTextView.text = shoppinglist?.product?.productname
            categoryTextView.text = "Categoria: " + shoppinglist?.product?.productcategory
            descriptionTextView.text = "Descripcion: " + shoppinglist?.product?.productdescription
            amountTextView.text = "Cantidad: " + shoppinglist?.product?.productamount.toString()
            priceTextView.text = "Precio: $" + shoppinglist?.product?.productprice.toString()
        }else{
            nameTextView.text = "Nombre:"
            categoryTextView.text = "Categoria:"
            descriptionTextView.text = "Descripcion:"
            amountTextView.text = "Cantidad:"
            priceTextView.text = "Precio: $"
        }

        return listItemView
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
