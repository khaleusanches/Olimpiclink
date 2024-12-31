package devsystem.olimpiclink.ui.pages

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import devsystem.olimpiclink.R
import devsystem.olimpiclink.databinding.ActivitySearchBinding
import devsystem.olimpiclink.model.User
import devsystem.olimpiclink.model.util.ApiCliente
import devsystem.olimpiclink.model.util.EndpointUser
import devsystem.olimpiclink.util.AdapterComunity
import devsystem.olimpiclink.util.AdapterEvent
import devsystem.olimpiclink.util.AdapterEventMini
import devsystem.olimpiclink.util.AdapterFriendsFollowsFollowers
import devsystem.olimpiclink.util.AdapterPublication
import devsystem.olimpiclink.util.CommonEvents
import devsystem.olimpiclink.util.EndpointComunity
import devsystem.olimpiclink.util.EndpointEvent
import devsystem.olimpiclink.util.EndpointPublication
import kotlinx.coroutines.launch

class SearchActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySearchBinding
    private var choice = "nada"
    private lateinit var api_comunity : EndpointComunity
    private lateinit var api_events : EndpointEvent
    private lateinit var api_users : EndpointUser
    private lateinit var api_publications : EndpointPublication
    private var commonEvents = CommonEvents()
    private lateinit var user : User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        user = intent.extras!!.getParcelable("user")!!
        binding.etSearchUser.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0.isNullOrEmpty()){
                    binding.rcComus.visibility = View.GONE
                    binding.rcPessoas.visibility = View.GONE
                    binding.rcPublications.visibility = View.GONE
                    binding.rcEvents.visibility = View.GONE
                }
                else{
                    searchChoice()
                }
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })

        api_publications = ApiCliente.retrofit.create(EndpointPublication::class.java)
        api_events = ApiCliente.retrofit.create(EndpointEvent::class.java)
        api_users = ApiCliente.retrofit.create(EndpointUser::class.java)
        api_comunity = ApiCliente.retrofit.create(EndpointComunity::class.java)

        commonEvents.goPageCreationPublication(user, this, binding.bottomAppbarCustom.binding.btnPgCreatePublication)
        commonEvents.goPageMain(user, this, binding.bottomAppbarCustom.binding.btnPgInitial)
        commonEvents.goPageMyProfile(user, this, binding.bottomAppbarCustom.binding.btnPgProfile)
        commonEvents.goPageComunity(user, this, binding.bottomAppbarCustom.binding.btnPgCommunities)
        commonEvents.goPageSearch(user, this, binding.bottomAppbarCustom.binding.btnPgSearch)
    }

    fun searchChoice(){
        if(choice == "comunidades"){searchComunidade()}
        else if(choice == "eventos"){searchEvents()}
        else if(choice == "pessoas"){searchUsers()}
        else if(choice == "publications"){searchPublications()}
    }

    private fun searchPublications() {
        lifecycleScope.launch {
            Log.d("MainActivity", "launch")
            try {
                var list_publication = api_publications.publicationsGetSearch(binding.etSearchUser.text.toString())
                val adapter = AdapterPublication(list_publication, this@SearchActivity, user)
                val rc = binding.rcPublications
                rc.layoutManager = LinearLayoutManager(this@SearchActivity)
                rc.setHasFixedSize(true)
                rc.adapter = adapter
            }
            catch (e: Exception) {
                Log.d("MainActivity", "launch2")
                e.printStackTrace()
            }
        }
    }

    private fun searchUsers() {
        lifecycleScope.launch {
            try {
                var list_followers = api_users.getSearchName(binding.etSearchUser.text.toString())
                val adapter = AdapterFriendsFollowsFollowers(this@SearchActivity, list_followers, this, 200, api_users, "FFF", user)
                binding.rcPessoas.layoutManager = LinearLayoutManager(this@SearchActivity)
                binding.rcPessoas.setHasFixedSize(true)
                binding.rcPessoas.adapter = adapter
            }
            catch (e : Exception){
                Log.d("FollowersActivity", "Erro")
            }
        }
    }

    private fun searchEvents() {
        lifecycleScope.launch {
            try {
                var listEvent = api_events.eventMiniGetSearch(binding.etSearchUser.text.toString())
                val adapterEvent = AdapterEvent(this@SearchActivity, user, listEvent)
                var rcEvent = binding.rcEvents
                rcEvent.layoutManager = LinearLayoutManager(this@SearchActivity, LinearLayoutManager.VERTICAL, false)
                rcEvent.setHasFixedSize(true)
                rcEvent.adapter = adapterEvent
            }
            catch (e: Exception){
                Log.d("MainActivity", "launch2")
                e.printStackTrace()
            }
        }
    }

    private fun searchComunidade() {
        lifecycleScope.launch {
            try {
                var lista_comus = api_comunity.getComunitySearch(binding.etSearchUser.text.toString())
                var adapter = AdapterComunity(this@SearchActivity, user, lista_comus, api_comunity)
                binding.rcComus.layoutManager = LinearLayoutManager(this@SearchActivity, LinearLayoutManager.VERTICAL, false)
                binding.rcComus.setHasFixedSize(true)
                binding.rcComus.adapter = adapter
            }
            catch (e:Exception){}
        }
    }

    fun comuOn(view: View) {
        limparTela()
        binding.rcComus.visibility = View.VISIBLE
        binding.tvComu.setTextColor(getColor(R.color.laranja_splash))
        choice = "comunidades"
        searchComunidade()
    }
    fun usersOn(view: View) {
        limparTela()
        binding.rcPessoas.visibility = View.VISIBLE
        binding.tvPessoas.setTextColor(getColor(R.color.laranja_splash))
        choice = "pessoas"
        searchUsers()
    }

    private fun limparTela() {
        binding.rcComus.visibility = View.GONE
        binding.rcPessoas.visibility = View.GONE
        binding.rcPublications.visibility = View.GONE
        binding.rcEvents.visibility = View.GONE
        binding.tvComu.setTextColor(getColor(R.color.black))
        binding.tvPublis.setTextColor(getColor(R.color.black))
        binding.tvEvents.setTextColor(getColor(R.color.black))
        binding.tvPessoas.setTextColor(getColor(R.color.black))
    }

    fun publicationsOn(view: View) {
        limparTela()
        binding.rcPublications.visibility = View.VISIBLE
        binding.tvPublis.setTextColor(getColor(R.color.laranja_splash))
        choice = "publications"
        searchPublications()
    }
    fun eventsOn(view: View) {
        limparTela()
        binding.rcEvents.visibility = View.VISIBLE
        binding.tvEvents.setTextColor(getColor(R.color.laranja_splash))
        choice = "eventos"
        searchEvents()
    }


}