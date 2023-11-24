package com.example.lircaymarket.Login

import android.annotation.SuppressLint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.lircaymarket.R
import com.example.lircaymarket.entity.SaveData

import com.example.lircaymarket.entity.User

class LoginActivity : AppCompatActivity() {

    private lateinit var emailText: EditText
    private lateinit var passwordText: EditText

    companion object{
        const val REQUEST_REGISTER = 1
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val appDatabase = SaveData.getDatabase(applicationContext)
        val userDao = appDatabase.userDao()

        emailText = findViewById(R.id.EmailText)
        passwordText = findViewById(R.id.PasswordText)

        val buttonOKLogin = findViewById<Button>(R.id.loginButton)

        buttonOKLogin.setOnClickListener{
            val email = emailText.text.toString()
            val password = passwordText.text.toString()

            if(email == "")
            {
                Toast.makeText(this, R.string.empty_email_error, Toast.LENGTH_SHORT).show()
            }else if(password == "")
            {
                Toast.makeText(this, R.string.empty_password_error, Toast.LENGTH_SHORT).show()
            }else {

                GlobalScope.launch(Dispatchers.IO)
                {
                    val users: List<User> = userDao.getAll()

                    for (i in users.indices) {
                        if (users[i].email == email && users[i].password == password) {
                            // Código para el usuario encontrado
                            // Puedes llamar a funciones de la interfaz de usuario dentro de runOnUiThread
                            runOnUiThread {
                                val intentMainActivity = Intent(this@LoginActivity, MainActivity::class.java)
                                    intentMainActivity.putExtra("useremail", email)
                                    startActivity(intentMainActivity)
                                    finish()

                            }
                            return@launch  // Sale del bucle cuando el usuario es encontrado
                        }
                    }

                    runOnUiThread {
                        Toast.makeText(this@LoginActivity, R.string.user_not_found_error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }
    fun goCreateUser(view: View) {

        val intentRegister = Intent(this, RegisterActivity::class.java)
        startActivityForResult(intentRegister, REQUEST_REGISTER)
    }
}