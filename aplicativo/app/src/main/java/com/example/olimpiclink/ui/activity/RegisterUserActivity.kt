package com.example.olimpiclink.ui.activity

import DAO
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.olimpiclink.databinding.ActivityRegisterUserBinding
import com.example.olimpiclink.models.User

class RegisterUserActivity : ComponentActivity() {
    private lateinit var binding: ActivityRegisterUserBinding
    private var dao = DAO();
    var username = ""
    var email = ""
    var password = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.activityRegisterUserRegister.setOnClickListener{
            pegandoDados()
            logar()
        }

    }

    private fun pegandoDados() {
        username = binding.activityRegisterUserCampoUser.text.toString()
        email = binding.activityRegisterUserCampoEmail.text.toString()
        password = binding.activityRegisterUserCampoPassword.text.toString()
    }

    private fun logar() {

        if(username == ""){
            Toast.makeText(this, "Username não inserido", Toast.LENGTH_SHORT).show()
        }
        else{
            var user = User(username, password, email)
            dao.registerUserDatabase(user)
            finish()
        }
    }
}