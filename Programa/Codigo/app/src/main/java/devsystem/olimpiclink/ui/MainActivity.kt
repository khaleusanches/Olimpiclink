package devsystem.olimpiclink.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import devsystem.olimpiclink.R
import devsystem.olimpiclink.databinding.ActivityMainBinding
import devsystem.olimpiclink.model.Publication
import devsystem.olimpiclink.model.User
import devsystem.olimpiclink.model.util.ApiCliente
import devsystem.olimpiclink.util.EndpointPublication
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var api_publication : EndpointPublication
    private lateinit var list_publication : List<Publication>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        window.navigationBarColor = resources.getColor(R.color.laranja_splash)
        window.statusBarColor = ContextCompat.getColor(this, R.color.laranja_splash)
        var user = intent.extras?.getParcelable<User>("user")
        api_publication = ApiCliente.retrofit.create(EndpointPublication::class.java)
        publicationGet()
    }

    private fun publicationGet() {
        Log.d("MainActivity", "dentro")
        lifecycleScope.launch {
            Log.d("MainActivity", "launch")
            try {
                Log.d("MainActivity", "launch1")
                list_publication = api_publication.publicationsGet()
                var imagens = list_publication[0].listarImagens()
                Log.d("MainActivity", "aa ${list_publication[0].url_image_one_publication}")
                if(imagens != null){
                    binding.pp.setTeste(
                        list_publication[0].login_user,
                        list_publication[0].text_publication,
                        list_publication[0].date_publication,
                        list_publication[0].url_profile_picture_user,
                        imagens
                    )
                    Log.d("MainActivity", imagens[0])
                }
                Log.d("MainActivity", imagens[0])
                // Manipule as publicações
            } catch (e: Exception) {
                // Lide com o erro
                Log.d("MainActivity", "launch2")
                e.printStackTrace()
            }
        }
    }
}