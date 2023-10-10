package com.example.lircaymarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.lircaymarket.entity.User
import com.example.lircaymarket.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var usernameText: EditText
    private lateinit var emailText: EditText
    private lateinit var passwordText: EditText
    private lateinit var repeatPasswordText: EditText
    private lateinit var registerButton : Button

    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        usernameText = findViewById(R.id.UsernameText)
        emailText = findViewById(R.id.EmailText)
        passwordText = findViewById(R.id.PasswordText)
        repeatPasswordText = findViewById(R.id.RepeatPasswordText)
        registerButton = findViewById(R.id.CreateAccountButton)

        registerButton.setOnClickListener{

            val username = usernameText.text.toString()
            val email = emailText.text.toString()
            val password = passwordText.text.toString()
            val repeatpassword = repeatPasswordText.text.toString()


            if(username == "")
            {
                Toast.makeText(this, R.string.empty_username_error, Toast.LENGTH_SHORT).show()
            }else if(email == "")
            {
                Toast.makeText(this, R.string.empty_email_error, Toast.LENGTH_SHORT).show()
            }else if(password == ""){
                Toast.makeText(this, R.string.empty_password_error, Toast.LENGTH_SHORT).show()
            }else if(password != repeatpassword)
            {
                Toast.makeText(this, R.string.repeat_password_error, Toast.LENGTH_SHORT).show()
            }else{
                //val user = User(2,username.toString(),password.toString(),email.toString(),2)
                val user = User(2,username,password,email,2)
                println("user : ${user}")
                val resultIntent = Intent()
                resultIntent.putExtra("new", user)
                setResult(RESULT_OK,resultIntent)
                finish()
            }
        }
    }
}