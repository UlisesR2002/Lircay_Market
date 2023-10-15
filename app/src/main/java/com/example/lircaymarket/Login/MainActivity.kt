package com.example.lircaymarket.Login

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.lircaymarket.entity.DataManager
import com.example.lircaymarket.PantryListActivity
import com.example.lircaymarket.R
import com.example.lircaymarket.ShopingListActivity
import com.example.lircaymarket.adapters.ProductPantryListAdapter
import com.example.lircaymarket.entity.Pantry
import com.example.lircaymarket.entity.Product
import com.example.lircaymarket.entity.Shoppinglist
import com.example.lircaymarket.entity.User


class MainActivity : AppCompatActivity() {

    private lateinit var user: User
    private lateinit var textwelcome : TextView
    private lateinit var  bienvenida : String

    companion object{
        const val REQUEST_REGISTER = 1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.optioncard_container)

        user = intent.getParcelableExtra<User>("users")!!

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

        if(DataManager.pantry.isEmpty()) {
            DataManager.pantry.add(
                Pantry(
                    user.pantryid,
                    Product(
                        DataManager.pantry.size + 1,
                        "Ravioli carne",
                        1,
                        "Paquete de 400 gramos de pasta rellena de marca carrozi",
                        "Alimento",
                        0
                    )
                )
            )

            DataManager.pantry.add(
                Pantry(
                    user.pantryid,
                    Product(
                        DataManager.pantry.size + 1,
                        "Coca-cola",
                        1,
                        "Botella de 2 litros de bebida marca coca-cola",
                        "Alimento",
                        0
                    )
                )
            )
        }

        if(DataManager.shoppinglist.isEmpty()) {
            DataManager.shoppinglist.add(
                Shoppinglist(
                    user.shoppinglistid,
                    Product(DataManager.shoppinglist.size + 1,
                        "Leche",
                        1,
                        "Caja de un litro de leche de marca colun",
                        "Alimento",
                        1000
                    )
                )
            )

            DataManager.shoppinglist.add(
                Shoppinglist(
                    user.shoppinglistid,
                    Product(
                        DataManager.shoppinglist.size + 1,
                        "Hamburguesa",
                        5,
                        "Paquete de hamburguesa individual de marca pf",
                        "Alimento",
                        200
                    )
                )
            )
        }

        optioncard1View.findViewById<TextView>(R.id.cardButtonNavigate).setOnClickListener{ view ->
            goPantryApp(view)
        }

        optioncard2View.findViewById<TextView>(R.id.cardButtonNavigate).setOnClickListener{ view ->
            goShoppinglistApp(view)
        }


    }

    fun goPantryApp(view: View){
        val intentPantryList = Intent(this, PantryListActivity::class.java)
        startActivity(intentPantryList)
    }

    fun goShoppinglistApp(view: View){
        val intentShoppingList = Intent(this, ShopingListActivity::class.java)
        startActivity(intentShoppingList)
    }
}