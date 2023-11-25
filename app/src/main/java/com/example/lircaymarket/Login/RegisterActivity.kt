package com.example.lircaymarket.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.lircaymarket.entity.SaveData
import com.example.lircaymarket.R
import com.example.lircaymarket.entity.Pantry
import com.example.lircaymarket.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    private lateinit var usernameText: EditText
    private lateinit var emailText: EditText
    private lateinit var passwordText: EditText
    private lateinit var repeatPasswordText: EditText
    private lateinit var registerButton: Button
    private var emailUsed: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        usernameText = findViewById(R.id.UsernameText)
        emailText = findViewById(R.id.EmailText)
        passwordText = findViewById(R.id.PasswordText)
        repeatPasswordText = findViewById(R.id.RepeatPasswordText)
        registerButton = findViewById(R.id.CreateAccountButton)

        val appDatabase = SaveData.getDatabase(applicationContext)
        val userDao = appDatabase.userDao()
        val pantryDao = appDatabase.pantryDao()

        registerButton.setOnClickListener {
            val username = usernameText.text.toString()
            val email = emailText.text.toString()
            val password = passwordText.text.toString()
            val repeatpassword = repeatPasswordText.text.toString()

            if (username == "") {
                Toast.makeText(this, R.string.empty_username_error, Toast.LENGTH_SHORT).show()
            } else if (email == "") {
                Toast.makeText(this, R.string.empty_email_error, Toast.LENGTH_SHORT).show()
            } else if (password == "") {
                Toast.makeText(this, R.string.empty_password_error, Toast.LENGTH_SHORT).show()
            } else if (password != repeatpassword) {
                Toast.makeText(this, R.string.repeat_password_error, Toast.LENGTH_SHORT).show()
            } else {
                emailUsed = false

                GlobalScope.launch(Dispatchers.IO) {
                    val users = userDao.getAll()
                    val pantries = pantryDao.getAll()
                    for (i in users.indices) {
                        if (users[i].email == email) {
                            launch(Dispatchers.Main) {
                                Toast.makeText(this@RegisterActivity, R.string.email_used_error, Toast.LENGTH_SHORT).show()
                            }
                            emailUsed = true
                        }
                    }

                    if (!emailUsed) {
                        val user = User(users.size + 1, username, password, email)
                        val pantry = Pantry(pantries.size + 1, users.size + 1)
                        userDao.insertAll(user)
                        pantryDao.insertAll(pantry)

                        launch(Dispatchers.Main) {
                            // Notificar el éxito y cerrar la actividad
                            val resultIntent = Intent()
                            setResult(RESULT_OK, resultIntent)
                            finish()
                        }
                    }
                }
            }
        }
    }
}