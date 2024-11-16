package devsystem.olimpiclink.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import devsystem.olimpiclink.R
import devsystem.olimpiclink.databinding.ActivityMoreSeeBinding
import devsystem.olimpiclink.util.AdapterEvent
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import devsystem.olimpiclink.model.EventMiniModelGet
import devsystem.olimpiclink.model.User
import devsystem.olimpiclink.model.util.ApiCliente
import devsystem.olimpiclink.util.CommonEvents
import devsystem.olimpiclink.util.EndpointEvent
import kotlinx.coroutines.launch

class MoreSeeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMoreSeeBinding
    private lateinit var rc_events : RecyclerView
    private lateinit var api_event : EndpointEvent
    private lateinit var listEvents : List<EventMiniModelGet>
    private lateinit var user : User
    private var recomended = true
    var commonEvents = CommonEvents()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMoreSeeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        window.navigationBarColor = resources.getColor(R.color.laranja_splash)
        window.statusBarColor = ContextCompat.getColor(this, R.color.laranja_splash)
        user = intent.extras!!.getParcelable<User>("user")!!
        recomended = intent.extras?.getBoolean("recomended") ?: true
        componentsInitialize()

    }

    private fun componentsInitialize() {
        rc_events = binding.recyclerView
        api_event = ApiCliente.retrofit.create(EndpointEvent::class.java)
        commonEvents.goPageCreationPublication(user, this, binding.bottomMenu.binding.btnPgCreatePublication)
        commonEvents.goPageMain(user, this, binding.bottomMenu.binding.btnPgInitial)
        binding.bottomMenu.binding.btnPgInitial.setImageResource(R.drawable.home_on)
        lifecycleScope.launch {
            try{
                if(recomended){
                    listEvents = api_event.eventMiniGet()
                }
                else{
                    listEvents = api_event.eventMiniGetSeguindo(user.id_user)
                }
                val adapter = AdapterEvent(this@MoreSeeActivity, listEvents)
                rc_events.layoutManager = LinearLayoutManager(this@MoreSeeActivity, LinearLayoutManager.VERTICAL, false)
                rc_events.setHasFixedSize(true)
                rc_events.adapter = adapter
            }
            catch (e : Exception){
                Log.d("MainActivity", "launch2")
                e.printStackTrace()
            }
        }
    }
}