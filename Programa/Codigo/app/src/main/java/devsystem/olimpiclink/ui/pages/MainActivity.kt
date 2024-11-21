package devsystem.olimpiclink.ui.pages

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import devsystem.olimpiclink.R
import devsystem.olimpiclink.databinding.ActivityMainBinding
import devsystem.olimpiclink.model.EventMiniModelGet
import devsystem.olimpiclink.model.PublicationModelGet
import devsystem.olimpiclink.model.User
import devsystem.olimpiclink.model.util.ApiCliente
import devsystem.olimpiclink.util.AdapterEventMini
import devsystem.olimpiclink.util.AdapterPublication
import devsystem.olimpiclink.util.CommonEvents
import devsystem.olimpiclink.util.EndpointEvent
import devsystem.olimpiclink.util.EndpointPublication
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    private lateinit var api_publication : EndpointPublication
    private lateinit var api_event : EndpointEvent

    private lateinit var list_publication : List<PublicationModelGet>
    private lateinit var listEvent : List<EventMiniModelGet>
    private lateinit var btn_seguindo : AppCompatButton
    private lateinit var btn_initial : AppCompatButton

    var commonEvents = CommonEvents()
    private lateinit var user : User
    var recomended = true;

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

        user = intent.extras!!.getParcelable<User>("user")!!

        componentsInitialize()
        publicationGet()
        eventMiniGet()
    }

    private fun componentsInitialize() {
        api_publication = ApiCliente.retrofit.create(EndpointPublication::class.java)
        api_event = ApiCliente.retrofit.create(EndpointEvent::class.java)
        commonEvents.goPageCreationPublication(user, this, binding.bottomMenu.binding.btnPgCreatePublication)
        commonEvents.goPageMain(user, this, binding.bottomMenu.binding.btnPgInitial)
        commonEvents.goPageMyProfile(user, this, binding.bottomMenu.binding.btnPgProfile)
        commonEvents.goPageComunity(user, this, binding.bottomMenu.binding.btnPgCommunities)

        btn_seguindo = binding.btnSeguindo
        btn_initial = binding.btnInicio
        binding.bottomMenu.binding.btnPgInitial.setImageResource(R.drawable.home_on)
    }

    private fun eventMiniGet() {
        lifecycleScope.launch {
            try {
                if(recomended){
                    listEvent = api_event.eventMiniGet()
                }
                else{
                    listEvent = api_event.eventMiniGetSeguindo(user.id_user)
                }
                val adapterEvent = AdapterEventMini(this@MainActivity, user, listEvent)
                var rcEvent = binding.rcEventsMini
                rcEvent.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                rcEvent.setHasFixedSize(true)
                rcEvent.adapter = adapterEvent
            }
            catch (e: Exception){
                Log.d("MainActivity", "launch2")
                e.printStackTrace()
            }
        }
    }
    private fun publicationGet() {
        Log.d("MainActivity", "dentro")
        lifecycleScope.launch {
            Log.d("MainActivity", "launch")
            try {
                Log.d("MainActivity", "launch1")
                if(recomended){
                    list_publication = api_publication.publicationsGet()
                }
                else{
                    list_publication = api_publication.publicationsGetSeguindo(user.id_user)
                }
                val adapter = AdapterPublication(list_publication, this@MainActivity, user)
                val rc = binding.rcFeed
                rc.layoutManager = LinearLayoutManager(this@MainActivity)
                rc.setHasFixedSize(true)
                rc.adapter = adapter

            }
            catch (e: Exception) {
                Log.d("MainActivity", "launch2")
                e.printStackTrace()
            }
        }
    }

    fun seguindoClick(view: View) {
        recomended = false;
        publicationGet()
        eventMiniGet()
        btn_seguindo.setBackgroundResource(R.drawable.button_border_red_selected)
        btn_seguindo.setTextColor(getColor(R.color.white))
        btn_initial.setBackgroundResource(R.drawable.button_border_red)
        btn_initial.setTextColor(getColor(R.color.red))

    }

    fun inicioClick(view: View) {
        recomended = true;
        publicationGet()
        eventMiniGet()
        btn_initial.setBackgroundResource(R.drawable.button_border_red_selected)
        btn_initial.setTextColor(getColor(R.color.white))
        btn_seguindo.setBackgroundResource(R.drawable.button_border_red)
        btn_seguindo.setTextColor(getColor(R.color.red))
    }

    fun moreNow(view: View) {
        var more_see = Intent(this, MoreSeeActivity::class.java)
        more_see.putExtra("user", user)
        more_see.putExtra("recomended", recomended)
        startActivity(more_see)
    }
}