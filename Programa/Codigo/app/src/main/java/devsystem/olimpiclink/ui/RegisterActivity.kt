package devsystem.olimpiclink.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import devsystem.olimpiclink.R
import devsystem.olimpiclink.databinding.ActivityRegisterBinding
import devsystem.olimpiclink.model.CommonButtonEvents
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    private lateinit var commonEvents : CommonButtonEvents
    private lateinit var et_username : EditText
    private lateinit var et_email : EditText
    private lateinit var et_password : EditText
    private lateinit var btn_continue : AppCompatButton

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        window.navigationBarColor = resources.getColor(R.color.end_initial)
        commonEvents = CommonButtonEvents()
        et_username = binding.etUsername
        et_email = binding.etEmail
        et_password = binding.etPassword
        btn_continue = binding.btnContinue

        et_username.onFocusChangeListener = commonEvents.focusChangedListenerGet(et_username)
        et_email.onFocusChangeListener = commonEvents.focusChangedListenerGet(et_email)
        et_password.onFocusChangeListener = commonEvents.focusChangedListenerGet(et_password)
        btn_continue.setOnTouchListener(commonEvents.touchListenerGet(btn_continue))
    }
}