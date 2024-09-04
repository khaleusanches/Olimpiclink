package devsystem.olimpiclink.ui

import devsystem.olimpiclink.util.CommonEvents
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import android.widget.EditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import devsystem.olimpiclink.R
import devsystem.olimpiclink.databinding.ActivityLoginBinding
import devsystem.olimpiclink.model.User
import devsystem.olimpiclink.model.util.ApiCliente
import devsystem.olimpiclink.model.util.Endpoint
import retrofit2.Call


class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private lateinit var commonEvents: CommonEvents
    private lateinit var btn_login : AppCompatButton
    private lateinit var et_username : EditText
    private lateinit var et_password : EditText
    private lateinit var api_user : Endpoint

    @Suppress("DEPRECATION")
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        window.navigationBarColor = resources.getColor(R.color.end_initial)
        componentsInitialize()
    }

    private fun userLogar(login_user : String, password_user : String) {
        api_user.userLogin(login_user, password_user).enqueue(object : retrofit2.Callback<User> {
            override fun onResponse(call: Call<User>, response: retrofit2.Response<User>){
                if (response.isSuccessful){
                    response.body()?.let { user ->
                        Log.d("LoginActivity", "User: ${user.name_user}")
                    }
                }else{
                    Log.e("LoginActivity", "Erro: ${response.code()}")
                }
            }
            override fun onFailure(call:Call<User>, t: Throwable){
                Log.e("LoginActivity", "falha", t)
            }
        })
    }

    private fun componentsInitialize() {
        commonEvents = CommonEvents()
        btn_login = binding.btnLogin
        et_username = binding.etUsername
        et_password = binding.etPassword

        api_user = ApiCliente.retrofit.create(Endpoint::class.java)

        et_username.onFocusChangeListener = commonEvents.focusChangedListenerGet(et_username)
        et_password.onFocusChangeListener = commonEvents.focusChangedListenerGet(et_password)
        btn_login.setOnTouchListener(commonEvents.touchListenerGet(btn_login))
    }

    fun enterClick(view: View) {
        if(et_username.text.isNotEmpty() && et_password.text.isNotEmpty()){
            userLogar(et_username.text.toString(), et_password.text.toString())
        }
    }
}