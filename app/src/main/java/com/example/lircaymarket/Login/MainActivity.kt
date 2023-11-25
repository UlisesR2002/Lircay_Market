package com.example.lircaymarket.Login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.lircaymarket.MarketListActivity
import com.example.lircaymarket.entity.SaveData
import com.example.lircaymarket.PantryListActivity
import com.example.lircaymarket.R
import com.example.lircaymarket.ShoppingListActivity
import com.example.lircaymarket.dao.UserDao
import com.example.lircaymarket.entity.Market
import com.example.lircaymarket.entity.Pantry
import com.example.lircaymarket.entity.Product
import com.example.lircaymarket.entity.Shoppinglist
import com.example.lircaymarket.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var user: User
    private lateinit var userDao: UserDao
    private lateinit var textwelcome: TextView
    private lateinit var bienvenida: String

    companion object{
        const val REQUEST_REGISTER = 1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.optioncard_container)

        val userEmail = intent.getStringExtra("useremail")


        textwelcome = findViewById(R.id.welcomeTextView)

        val appDatabase = SaveData.getDatabase(applicationContext)
        userDao = appDatabase.userDao()

        GlobalScope.launch(Dispatchers.IO) {
            // Buscar el usuario por email en la base de datos
            val userFromDatabase = userDao.getUserByEmail(userEmail.orEmpty())

            // Verificar si se encontró el usuario
            if (userFromDatabase != null) {
                // Configurar bienvenida después de obtener la información del usuario
                val welcometext = getString(R.string.welcome_user_text)
                bienvenida = welcometext + " " + userFromDatabase.username

                // Actualizar la UI en el hilo principal
                runOnUiThread {
                    textwelcome.text = bienvenida
                }
            } else {
                // Manejar el caso de que el usuario no se encuentre en la base de datos
                // Puedes mostrar un mensaje de error o tomar la acción apropiada
            }
        }

        textwelcome.setText(userEmail)

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

        /*if(SaveData.pantry.isEmpty()) {
            SaveData.pantry.add(
                Pantry(
                    SaveData.pantry.size + 1,
                    Product(
                        SaveData.pantry.size + 1,
                        "Ravioli carne",
                        1,
                        "Paquete de 400 gramos de pasta rellena de marca carrozi",
                        "Alimento",
                        0
                    )
                )
            )

            SaveData.pantry.add(
                Pantry(
                    SaveData.pantry.size + 1,
                    Product(
                        SaveData.pantry.size + 1,
                        "Coca-cola",
                        1,
                        "Botella de 2 litros de bebida marca coca-cola",
                        "Alimento",
                        0
                    )
                )
            )
        }

        if(SaveData.shoppinglist.isEmpty()) {
            SaveData.shoppinglist.add(
                Shoppinglist(
                    SaveData.shoppinglist.size + 1,
                    Product(SaveData.shoppinglist.size + 1,
                        "Leche",
                        1,
                        "Caja de un litro de leche de marca colun",
                        "Alimento",
                        1000
                    )
                )
            )

            SaveData.shoppinglist.add(
                Shoppinglist(
                    SaveData.shoppinglist.size + 1,
                    Product(
                        SaveData.shoppinglist.size + 1,
                        "Hamburguesa",
                        5,
                        "Paquete de hamburguesa individual de marca pf",
                        "Alimento",
                        200
                    )
                )
            )
        }

        if(SaveData.Market.isEmpty()) {
            SaveData.Market.add(
                Market(
                    SaveData.Market.size + 1,
                    "Unimarc",
                    "Supermercado",
                    "Cinco Norte 3615"
                    )
                )


            SaveData.Market.add(
                Market(
                        SaveData.Market.size + 1,
                        "Lider Talca",
                        "Supermercado",
                        "Dos Norte 1422"
                    )
                )

        }
        */
        optioncard1View.findViewById<TextView>(R.id.cardButtonNavigate).setOnClickListener{ view ->
            goPantryApp(view)
        }

        optioncard2View.findViewById<TextView>(R.id.cardButtonNavigate).setOnClickListener{ view ->
            goShoppinglistApp(view)
        }

        optioncard3View.findViewById<TextView>(R.id.cardButtonNavigate).setOnClickListener{ view ->
            goMarketApp(view)
        }


    }

    fun goPantryApp(view: View){
        val intentPantryList = Intent(this, PantryListActivity::class.java)
        startActivity(intentPantryList)
    }

    fun goShoppinglistApp(view: View){
        val intentShoppingList = Intent(this, ShoppingListActivity::class.java)
        startActivity(intentShoppingList)
    }

    fun goMarketApp(view: View){
        val intentMarketList = Intent(this, MarketListActivity::class.java)
        startActivity(intentMarketList)
    }
}