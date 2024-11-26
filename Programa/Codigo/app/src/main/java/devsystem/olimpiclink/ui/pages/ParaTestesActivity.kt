package devsystem.olimpiclink.ui.pages

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import devsystem.olimpiclink.R
import devsystem.olimpiclink.databinding.ActivityParaTestesBinding

class ParaTestesActivity : AppCompatActivity() {
    private lateinit var binding : ActivityParaTestesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityParaTestesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Glide.with(this).load("http://192.168.0.158:5000/api/comunities/banner/8").into(binding.imgBanner)
        Glide.with(this).load("https://claraaraujo2.github.io/Olimpiclink/Programa/Design_e_Modelagem/telas/boxred.png").into(binding.btnCategory)
        Glide.with(this).load("https://proece.ufms.br/files/2021/05/Qdd_Geral-1024x1024.jpg").circleCrop().into(binding.imgComunityIcon)
    }
}