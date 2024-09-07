package devsystem.olimpiclink.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import devsystem.olimpiclink.R
import devsystem.olimpiclink.databinding.ActivityRegisterCityBinding
import devsystem.olimpiclink.model.User
import android.widget.EditText
import devsystem.olimpiclink.util.EndpointCity

class RegisterCityActivity: AppCompatActivity() {
    private lateinit var binding : ActivityRegisterCityBinding;
    private lateinit var et_state : EditText;
    private lateinit var et_city : EditText;
    private lateinit var api_city : EndpointCity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterCityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        componentsInitialize()
        val user = intent.extras?.getParcelable<User>("user")
    }

    private fun componentsInitialize() {
        et_state = binding.etState
        et_city = binding.etCity
    }
}