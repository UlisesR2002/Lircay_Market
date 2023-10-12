package com.example.lircaymarket

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.lircaymarket.entity.Pantry
import com.example.lircaymarket.entity.Product
import com.example.lircaymarket.entity.User

class PantryListActivity : AppCompatActivity() {

    private lateinit var  listViewProducts: ListView
    private var listOption: Boolean = true
    private var detailOption: Boolean = false
    private lateinit var user: User
    private lateinit var pantry: Pantry

    companion object{
        const val REQUEST_REGISTER = 1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantry_list)

        user = intent.getParcelableExtra<User>("users")!!

        pantry = Pantry(user.pantryid, arrayListOf<Product>())

    }
}