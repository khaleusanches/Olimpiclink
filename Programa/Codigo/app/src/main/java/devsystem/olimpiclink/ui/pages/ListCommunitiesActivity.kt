package devsystem.olimpiclink.ui.pages

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import devsystem.olimpiclink.R
import devsystem.olimpiclink.databinding.ActivityListCommunitiesBinding
import devsystem.olimpiclink.model.util.ApiCliente
import devsystem.olimpiclink.util.AdapterComunity
import devsystem.olimpiclink.util.EndpointComunity
import kotlinx.coroutines.launch

class ListCommunitiesActivity : AppCompatActivity() {
    private lateinit var binding : ActivityListCommunitiesBinding
    private lateinit var api_comunity : EndpointComunity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityListCommunitiesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        api_comunity = ApiCliente.retrofit.create(EndpointComunity::class.java)
        inicializar()
    }

    private fun inicializar() {
        lifecycleScope.launch {
            try {
                var lista_comus = api_comunity.getComunityCardId()
                var adapter = AdapterComunity(this@ListCommunitiesActivity, lista_comus)
                binding.rcComus.layoutManager = LinearLayoutManager(this@ListCommunitiesActivity, LinearLayoutManager.VERTICAL, false)
                binding.rcComus.setHasFixedSize(true)
                binding.rcComus.adapter = adapter
            }
            catch (e:Exception){}
        }
    }

}