package com.example.lircaymarket.Login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.lircaymarket.entity.DataManager
import com.example.lircaymarket.R

import com.example.lircaymarket.entity.User

class LoginActivity : AppCompatActivity() {

    private lateinit var emailText: EditText
    private lateinit var passwordText: EditText
    private var accountFound : Boolean = false

    companion object{
        const val REQUEST_REGISTER = 1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        DataManager.AddUser(User(1,"Laboratorio","1234","ADMIN",1,1))

        var users = DataManager.GetUsers()

        emailText = findViewById(R.id.EmailText)
        passwordText = findViewById(R.id.PasswordText)

        val buttonOKLogin = findViewById<Button>(R.id.loginButton);

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
                for (i in users.indices) {
                    println("user : ${users[i]}")
                    if (users[i].email.toString() == email && users[i].password.toString() == password) {
                        accountFound = true
                        val intentMainActivity = Intent(this, MainActivity::class.java)

                        intentMainActivity.putExtra("users", users[i])
                        startActivityForResult(intentMainActivity, REQUEST_REGISTER)

                        //startActivity(intentMainActivity)
                    }
                }

                if(!accountFound)
                {
                    Toast.makeText(this, R.string.user_not_found_error, Toast.LENGTH_SHORT).show()
                }

            }
        }

    }
    fun goCreateUser(view: View) {
        val intentRegister = Intent(this, RegisterActivity::class.java)
        startActivityForResult(intentRegister, REQUEST_REGISTER)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_REGISTER && resultCode == RESULT_OK) {
            val newUser = data?.getParcelableExtra<User>("new")
            if(newUser != null){

                DataManager.AddUser(newUser)

            }
        }
    }
}