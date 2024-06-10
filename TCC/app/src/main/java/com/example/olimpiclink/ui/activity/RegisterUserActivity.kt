package com.example.olimpiclink.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Button
import androidx.activity.ComponentActivity
import com.example.olimpiclink.R
import com.example.olimpiclink.models.User

class RegisterUserActivity : ComponentActivity() {
    var campoUser = findViewById<EditText>(R.id.activity_register_user_campo_user)
    var campoEmail = findViewById<EditText>(R.id.activity_register_user_campo_Email)
    var campoPassword = findViewById<EditText>(R.id.activity_register_user_campo_password)
    var btnCadastrar = findViewById<Button>(R.id.activity_register_user_register)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)
        
    }

    private fun getUser() : User{
        var username = campoUser.text.toString();
        var password = campoPassword.text.toString();
        var email = campoEmail.text.toString();
        return User(username, password, email);
    }
}