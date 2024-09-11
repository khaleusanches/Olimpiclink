package devsystem.olimpiclink.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import devsystem.olimpiclink.R
import devsystem.olimpiclink.databinding.ActivityUnpublishedPublicationBinding
import devsystem.olimpiclink.model.User

class UnpublishedPublicationActivity : AppCompatActivity() {
    private lateinit var binding : ActivityUnpublishedPublicationBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUnpublishedPublicationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        window.navigationBarColor = resources.getColor(R.color.laranja_splash)
        window.statusBarColor = ContextCompat.getColor(this, R.color.laranja_splash)
        var user = intent.extras?.getParcelable<User>("user")
        Glide.with(this).load("http://192.168.0.158:5000/api/publication/imagens/1/2").into(binding.imgOne)
        Glide.with(this).load("http://192.168.0.158:5000/api/publication/imagens/1/2").into(binding.imgTwo)
        Glide.with(this).load("http://192.168.0.158:5000/api/publication/imagens/1/2").into(binding.imgThree)
        Glide.with(this).load("http://192.168.0.158:5000/api/publication/imagens/1/2").into(binding.imgFour)
    }
}