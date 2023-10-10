package com.example.lircaymarket

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
//import com.example.lircaymarket.adapters.

import com.example.lircaymarket.entity.User

class LoginActivity : AppCompatActivity() {

    private lateinit var users: MutableList<User>
   // private lateinit var adapterItems: UserListAdapter
    private lateinit var emailText: EditText
    private lateinit var passwordText: EditText

    companion object{
        const val REQUEST_REGISTER = 1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        users = mutableListOf(
            User(1,"Laboratorio","Utalca-IDVRV","lab@gmail.com",1),
        )


        emailText = findViewById(R.id.EmailText)
        passwordText = findViewById(R.id.PasswordText)

        val buttonOKLogin = findViewById<Button>(R.id.loginButton);

        buttonOKLogin.setOnClickListener{
            val email = emailText.text.toString()
            val password = passwordText.text.toString()

            for (i in users.indices)
            {
                println("user : ${users[i]}")
                if(users[i].email.toString() == email && users[i].password.toString() == password)
                {
                    val intentMainActivity = Intent(this, MainActivity::class.java)
                    startActivity(intentMainActivity)
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
                users.add(newUser)


            }
        }
    }
}