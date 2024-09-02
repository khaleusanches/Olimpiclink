package devsystem.olimpiclink.ui

import devsystem.olimpiclink.model.CommonButtonEvents
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import android.widget.EditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import devsystem.olimpiclink.R
import devsystem.olimpiclink.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private lateinit var buttonEvents: CommonButtonEvents
    private lateinit var btn_login : AppCompatButton
    private lateinit var et_username : EditText
    private lateinit var et_password : EditText

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
        buttonEvents = CommonButtonEvents()
        btn_login = binding.btnLogin
        et_username = binding.etUsername
        et_password = binding.etPassword

        window.navigationBarColor = resources.getColor(R.color.end_initial)
        et_username.setOnFocusChangeListener(buttonEvents.focusChangedListenerGet(et_username))
        et_password.setOnFocusChangeListener(buttonEvents.focusChangedListenerGet(et_password))
        btn_login.setOnTouchListener(buttonEvents.touchListenerGet(btn_login))
    }
}