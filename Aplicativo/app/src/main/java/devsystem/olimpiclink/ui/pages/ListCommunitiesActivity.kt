package devsystem.olimpiclink.ui.pages

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import devsystem.olimpiclink.R
import devsystem.olimpiclink.databinding.ActivityListCommunitiesBinding
import devsystem.olimpiclink.model.User
import devsystem.olimpiclink.model.util.ApiCliente
import devsystem.olimpiclink.util.AdapterComunity
import devsystem.olimpiclink.util.CommonEvents
import devsystem.olimpiclink.util.EndpointComunity
import kotlinx.coroutines.launch

class ListCommunitiesActivity : AppCompatActivity() {
    private lateinit var binding : ActivityListCommunitiesBinding
    private lateinit var api_comunity : EndpointComunity
    private lateinit var user : User
    private var commonEvents = CommonEvents()
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

        window.navigationBarColor = resources.getColor(R.color.laranja_splash)
        window.statusBarColor = ContextCompat.getColor(this, R.color.laranja_splash)
        api_comunity = ApiCliente.retrofit.create(EndpointComunity::class.java)
        user = intent.extras!!.getParcelable("user")!!
        inicializar()
    }

    private fun inicializar() {
        lifecycleScope.launch {
            try {
                var lista_comus = api_comunity.getComunityCardId(user.id_user)
                var adapter = AdapterComunity(this@ListCommunitiesActivity, user, lista_comus, api_comunity)
                binding.rcComus.layoutManager = LinearLayoutManager(this@ListCommunitiesActivity, LinearLayoutManager.VERTICAL, false)
                binding.rcComus.setHasFixedSize(true)
                binding.rcComus.adapter = adapter
            }
            catch (e:Exception){}
        }
        commonEvents.goPageCreationPublication(user, this, binding.bottomMenu.binding.btnPgCreatePublication)
        commonEvents.goPageMain(user, this, binding.bottomMenu.binding.btnPgInitial)
        commonEvents.goPageMyProfile(user, this, binding.bottomMenu.binding.btnPgProfile)
        commonEvents.goPageComunity(user, this, binding.bottomMenu.binding.btnPgCommunities)
        commonEvents.goPageSearch(user, this, binding.bottomMenu.binding.btnPgSearch)

    }

    fun createComunity(view: View) {
        var main_activity = Intent(this, CreateComunityActivity::class.java)
        main_activity.putExtra("user", user)
        startActivity(main_activity)
    }

}