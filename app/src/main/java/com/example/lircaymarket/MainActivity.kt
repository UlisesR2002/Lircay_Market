package com.example.lircaymarket

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.lircaymarket.entity.User


class MainActivity : AppCompatActivity() {

    private lateinit var user: User
    private lateinit var textwelcome : TextView
    private lateinit var  bienvenida : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.optioncard_container)


        user = intent.getParcelableExtra<User>("users")!!

        println("user : ${user}")
        bienvenida = "Bienvenid@ " + user.username

        textwelcome = findViewById(R.id.welcomeTextView)
        textwelcome.setText(bienvenida)

        val optioncard1View = findViewById<View>(R.id.card1)
        val optioncard2View = findViewById<View>(R.id.card2)
        val optioncard3View = findViewById<View>(R.id.card3)

        optioncard1View.findViewById<TextView>(R.id.cardTextViewTitle).setText(R.string.optioncard1_title_text)
        optioncard2View.findViewById<TextView>(R.id.cardTextViewTitle).setText(R.string.optioncard2_title_text)
        optioncard3View.findViewById<TextView>(R.id.cardTextViewTitle).setText(R.string.optioncard3_title_text)

        optioncard1View.findViewById<TextView>(R.id.cardTextViewDescription).setText(R.string.optioncard1_description_text)
        optioncard2View.findViewById<TextView>(R.id.cardTextViewDescription).setText(R.string.optioncard2_description_text)
        optioncard3View.findViewById<TextView>(R.id.cardTextViewDescription).setText(R.string.optioncard3_description_text)

        optioncard1View.findViewById<Button>(R.id.cardButtonNavigate).setText(R.string.optioncard1_button_text)
        optioncard2View.findViewById<Button>(R.id.cardButtonNavigate).setText(R.string.optioncard2_button_text)
        optioncard3View.findViewById<Button>(R.id.cardButtonNavigate).setText(R.string.optioncard3_button_text)

        optioncard1View.findViewById<TextView>(R.id.cardButtonNavigate).setOnClickListener{view ->
            goPantryApp(view)
        }
    }

    fun goPantryApp(view: View){
        val intentPantryList = Intent(this, PantryListActivity::class.java)
        intentPantryList.putExtra("user", user)
        startActivity(intentPantryList)
    }

}