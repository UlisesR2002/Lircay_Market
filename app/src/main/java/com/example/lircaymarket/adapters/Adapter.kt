package com.example.lircaymarket.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.lifecycle.LifecycleCoroutineScope
import com.example.lircaymarket.R
import com.example.lircaymarket.entity.Movement
import com.example.lircaymarket.entity.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface ProductEditListener {
    fun onEditProduct(productid: Int)
}

class ProductPantryListAdapter(
    context: Context,
    resource: Int,
    products: List<Product>,
    private val pantryId: Int,
    private val editListener: ProductEditListener
) : ArrayAdapter<Product>(context, resource, products.filter { it.pantryid == pantryId }) {

    @SuppressLint("SetTextI18n")
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

            val categoryText = context.getString(R.string.product_category_text)
            categoryTextView.text = "$categoryText ${product.productcategory}"

            val descriptionText = context.getString(R.string.product_decription_text)
            descriptionTextView.text = "$descriptionText ${product.productdescription}"

            val amountText = context.getString(R.string.product_amount_text)
            amountTextView.text = "$amountText ${product.productamount}"
        }

        listItemView.setOnClickListener {
            product?.let { editListener.onEditProduct(it.productid) }
        }
        return listItemView
    }
}


class ProductShoppingListAdapter(
    context: Context,
    resource: Int,
    products: List<Product>,
    val shoppinglistId : Int,
    val editListener: ProductEditListener

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
            val categoryText = context.getString(R.string.product_category_text)
            categoryTextView.text = "$categoryText ${product.productcategory}"

            val descriptionText = context.getString(R.string.product_decription_text)
            descriptionTextView.text = "$descriptionText ${product.productdescription}"

            val amountText = context.getString(R.string.product_amount_text)
            amountTextView.text = "$amountText ${product.productamount}"

            val priceText = context.getString(R.string.product_price_text)
            priceTextView.text = "$priceText ${product.productprice}"
        }
        listItemView.setOnClickListener {
            product?.let { editListener.onEditProduct(it.productid) }
        }
        return listItemView
    }
}

class MovementListAdapter(
    context: Context,
    resource: Int,
    movements: List<Movement>,
    private val userid: Int,
    private val lifecycleScope: LifecycleCoroutineScope
) : ArrayAdapter<Movement>(context, resource, movements.filter { it.userid == userid }) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val listItemView = convertView ?: inflater.inflate(R.layout.list_item_movement, null)

        // Obtener el movimiento en la posición actual
        val movement = getItem(position)

        // Bind patient data to TextViews in the custom layout
        val movementTextView = listItemView.findViewById<TextView>(R.id.textViewMovement)

        if (movement != null && movement.userid == userid) {
            // Mostrar el movimiento solo si el userid coincide
            lifecycleScope.launchWhenCreated {
                val resources = context.resources
                val message = when (movement.movementtype) {
                    1 -> "${movement.productname} ${resources.getString(R.string.create_in_pantry)}"
                    2 -> "${movement.productname} ${resources.getString(R.string.update_in_pantry)}"
                    3 -> "${movement.productname} ${resources.getString(R.string.delete_in_pantry)}"
                    4 -> "${movement.productname} ${resources.getString(R.string.create_in_shoppinglist)}"
                    5 -> "${movement.productname} ${resources.getString(R.string.update_in_shoppinglist)}"
                    6 -> "${movement.productname} ${resources.getString(R.string.delete_in_shoppinglist)}"
                    else -> ""
                }
                withContext(Dispatchers.Main) {
                    movementTextView.text = message
                }
            }
        }
        return listItemView
    }
}