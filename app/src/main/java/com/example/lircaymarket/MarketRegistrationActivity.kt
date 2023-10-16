package com.example.lircaymarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.lircaymarket.entity.SaveData
import com.example.lircaymarket.entity.Market

class MarketRegistrationActivity : AppCompatActivity() {
    private lateinit var nameText: EditText
    private lateinit var typeText: EditText
    private lateinit var directionText: EditText
    private lateinit var SaveProductButton : Button
    private lateinit var CancelProductButton : Button


    private var editMarket: Market? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market_registration)

        nameText = findViewById(R.id.editTextName)
        typeText = findViewById(R.id.editTextType)
        directionText = findViewById(R.id.editTextDirection)
        SaveProductButton = findViewById(R.id.buttonSave)
        CancelProductButton = findViewById(R.id.buttonCancel)

        val index = intent.getIntExtra("index", -1)

        if(index >= 0)
        {
            editMarket = SaveData.Market[index]
        }

        if(editMarket == null){

            SaveProductButton.setOnClickListener{
                onCreateMarket()
            }
            CancelProductButton.setOnClickListener {
                finish()
            }
        }else{
            nameText.setText(editMarket?.marketname)
            typeText.setText(editMarket?.markettype)
            directionText.setText(editMarket?.marketdirection)

            SaveProductButton.setOnClickListener {
                onEditMarket()
            }
            CancelProductButton.setText(R.string.borrar_button)
            CancelProductButton.setOnClickListener {
                onDeleteMarket()
            }
        }
    }

    fun onCreateMarket(){
        val name = nameText.text.toString()
        val type = typeText.text.toString()
        val direction = directionText.text.toString()

        if(name == ""){

            Toast.makeText(this, R.string.empty_username_error, Toast.LENGTH_SHORT).show()
        }else if(type == ""){

            Toast.makeText(this, R.string.empty_type_error, Toast.LENGTH_SHORT).show()
        }else if(direction == ""){

            Toast.makeText(this, R.string.empty_direction_error, Toast.LENGTH_SHORT).show()
        }else{
            SaveData.Market.add(
                Market(
                    SaveData.Market.size + 1,
                    name,
                    type,
                    direction
                )
            )
            finish()
        }
    }

    fun onEditMarket(){
        val name = nameText.text.toString()
        val type = typeText.text.toString()
        val direction = directionText.text.toString()

        editMarket?.marketname = name
        editMarket?.markettype = type
        editMarket?.marketdirection = direction

        finish()
    }

    fun onDeleteMarket(){
        SaveData.Market.remove(editMarket)
        finish()
    }


}