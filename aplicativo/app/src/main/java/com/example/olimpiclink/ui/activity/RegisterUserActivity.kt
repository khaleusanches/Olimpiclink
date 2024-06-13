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
    var campoUser : EditText? = null;
    var campoEmail : EditText? = null;
    var campoPassword : EditText? = null;
    var btnCadastrar : Button? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)
        campoUser = findViewById<EditText>(R.id.activity_register_user_campo_user)
        campoEmail = findViewById<EditText>(R.id.activity_register_user_campo_Email)
        campoPassword = findViewById<EditText>(R.id.activity_register_user_campo_password)
        btnCadastrar = findViewById<Button>(R.id.activity_register_user_register)
    }


}