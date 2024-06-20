package com.example.olimpiclink.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.olimpiclink.databinding.ActivityLoginBinding

class LoginActivity : ComponentActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate((layoutInflater))
        setContentView(binding.root)
        binding.button.setOnClickListener{
        }
    }

}