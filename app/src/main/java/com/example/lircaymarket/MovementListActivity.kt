package com.example.lircaymarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import androidx.lifecycle.lifecycleScope
import com.example.lircaymarket.adapters.MovementListAdapter
import com.example.lircaymarket.adapters.ProductPantryListAdapter
import com.example.lircaymarket.entity.Movement
import com.example.lircaymarket.entity.SaveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovementListActivity : AppCompatActivity() {

    private lateinit var listViewMovement: ListView
    private lateinit var movements: List<Movement>
    private var userid: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movement_list)

        userid = intent.getIntExtra("userid",0)

        lifecycleScope.launch {

            movements = withContext(Dispatchers.IO) {
                SaveData.getDatabase(applicationContext).movementDao().getAll().reversed()
            }
            setupUI()
        }
    }

    private fun setupUI() {
        listViewMovement = findViewById(R.id.listViewMovement)

        listViewMovement.adapter = MovementListAdapter(
            applicationContext,
            android.R.layout.simple_list_item_1,
            movements,
            userid,
            lifecycleScope
        )
    }
}