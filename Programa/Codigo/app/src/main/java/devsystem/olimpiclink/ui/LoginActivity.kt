package devsystem.olimpiclink

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent.ACTION_CANCEL
import android.view.MotionEvent.ACTION_DOWN
import android.view.MotionEvent.ACTION_UP
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import devsystem.olimpiclink.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
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
        binding.etUsername.setOnFocusChangeListener{ v, hasFocus->
            if(hasFocus){
                binding.etUsername.setBackgroundResource(R.drawable.edit_text_selected)
            }
            else{
                binding.etUsername.setBackgroundResource(R.drawable.buttons_initial)
            }
        }
        binding.etPassword.setOnFocusChangeListener{v, hasFocus->
            if(hasFocus){
                binding.etPassword.setBackgroundResource(R.drawable.edit_text_selected)
            }
            else{
                binding.etPassword.setBackgroundResource(R.drawable.buttons_initial)
            }
        }
        binding.btnLogin.setOnTouchListener{view, motionEvent ->
            when (motionEvent.action) {
                ACTION_DOWN -> {
                    binding.btnLogin.setBackgroundResource(R.drawable.edit_text_selected)
                    binding.btnLogin.setTextColor(getColor(R.color.laranja_splash))
                }
                ACTION_UP, ACTION_CANCEL -> {
                    binding.btnLogin.setBackgroundResource(R.drawable.buttons_initial)
                    binding.btnLogin.setTextColor(getColor(R.color.white))
                }
            }
            false
        }
    }
}