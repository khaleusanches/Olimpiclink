package devsystem.olimpiclink.ui.pages

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import devsystem.olimpiclink.R
import devsystem.olimpiclink.databinding.ActivityComunityBinding
import devsystem.olimpiclink.model.ComunityModel
import devsystem.olimpiclink.model.FollowComunityModel
import devsystem.olimpiclink.model.PublicationModelGet
import devsystem.olimpiclink.model.RequestMessages
import devsystem.olimpiclink.model.RequestParticipationComunityModel
import devsystem.olimpiclink.model.User
import devsystem.olimpiclink.model.util.ApiCliente
import devsystem.olimpiclink.util.AdapterEvent
import devsystem.olimpiclink.util.AdapterPublication
import devsystem.olimpiclink.util.CommonEvents
import devsystem.olimpiclink.util.EndpointComunity
import devsystem.olimpiclink.util.EndpointEvent
import devsystem.olimpiclink.util.EndpointPublication
import kotlinx.coroutines.launch

class ComunityActivity : AppCompatActivity() {
    private lateinit var binding : ActivityComunityBinding
    private lateinit var user : User
    private var comunity_id = 0
    private var commonEvents = CommonEvents()
    //api
    private lateinit var api_comunity : EndpointComunity
    private lateinit var comunity : ComunityModel
    private lateinit var message: RequestMessages
    private lateinit var message_participation: RequestMessages

    private lateinit var api_publication : EndpointPublication
    private lateinit var list_publication : List<PublicationModelGet>
    private lateinit var api_events : EndpointEvent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityComunityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        window.navigationBarColor = resources.getColor(R.color.laranja_splash)
        window.statusBarColor = ContextCompat.getColor(this, R.color.laranja_splash)
        user = intent.extras!!.getParcelable("user")!!
        comunity_id = intent.extras!!.getInt("comunity_id")

        api_comunity = ApiCliente.retrofit.create(EndpointComunity::class.java)


        api_publication = ApiCliente.retrofit.create(EndpointPublication::class.java)
        api_events = ApiCliente.retrofit.create(EndpointEvent::class.java)
        componentsInitialize()
    }

    private fun componentsInitialize() {
        comunityLoad{
            Glide.with(this).load(comunity.url_icon_comunity).circleCrop().into(binding.imgComunityIcon)
            Glide.with(this).load(comunity.category_icon).into(binding.btnCategory)
            Glide.with(this).load(comunity.url_banner_comunity).into(binding.imgBanner)
            binding.tvComunityName.text = comunity.name_comunity
            binding.tvFollows.text = comunity.n_seguidores.toString() + " seguidores"
            binding.tvParticipantes.text = comunity.n_participantes.toString() + " participantes"
            binding.tvBio.text = comunity.description_comunity
            binding.tvRegras.text = comunity.regras_comunity

            publicationGet()
            seguindoOrNot()
            requestParticipation()
        }
        commonEvents.goPageCreationPublication(user, this, binding.bottomAppbarCustom.binding.btnPgCreatePublication)
        commonEvents.goPageMain(user, this, binding.bottomAppbarCustom.binding.btnPgInitial)
        commonEvents.goPageMyProfile(user, this, binding.bottomAppbarCustom.binding.btnPgProfile)
        commonEvents.goPageComunity(user, this, binding.bottomAppbarCustom.binding.btnPgCommunities)
    }

    private fun requestParticipation() {
        lifecycleScope.launch {
            try {
                message_participation = api_comunity.getRequestParticipation(user.id_user, comunity.id_comunity)
                if (message_participation.message == "Pedir"){
                    binding.btnParticipante.text = "Pedir para participar"
                }
                else{
                    binding.btnParticipante.text = "Pedido em analíse"
                }
            }
            catch (e : Exception){}
        }
    }

    private fun seguindoOrNot() {
        lifecycleScope.launch {
            try {
                message = api_comunity.comunityFF(user.id_user, comunity.id_comunity)
                if(message.message == "Segue"){
                    binding.btnSeguir.text = "Segue"
                }
                else if (message.message == "Participa"){
                    binding.btnSeguir.text = "Participa"
                    binding.btnParticipante.visibility = View.GONE
                }
                else{
                    binding.btnSeguir.text = "Seguir"
                }
            }
            catch (e : Exception){}
        }
    }

    private fun comunityLoad(onComunityLoaded: () -> Unit) {
        lifecycleScope.launch {
            try {
                comunity = api_comunity.getComunityId(comunity_id)
            }
            catch (e : Exception){}
            onComunityLoaded()
        }
    }

    private fun publicationGet() {
        lifecycleScope.launch {
            try {
                list_publication = api_publication.publicationsGetComunity(comunity.id_comunity)
                val adapter = AdapterPublication(list_publication, this@ComunityActivity, user)
                val rc = binding.rcFeed
                rc.layoutManager = LinearLayoutManager(this@ComunityActivity)
                rc.setHasFixedSize(true)
                rc.adapter = adapter

            }
            catch (e: Exception) {
                Log.d("MainActivity", "launch2")
                e.printStackTrace()
            }
        }
    }

    fun seguirComunity(view: View) {
        lifecycleScope.launch {
            try {
                if (message.message == "Segue") {
                    api_comunity.comunityDesFollow(user.id_user, comunity.id_comunity)
                } else if (message.message == "Seguir") {
                    api_comunity.comunityFollow(FollowComunityModel(0, user.id_user, comunity.id_comunity))
                }
                seguindoOrNot()
                comunityLoad{
                    binding.tvFollows.text = comunity.n_seguidores.toString() + " seguidores"
                    binding.tvParticipantes.text = comunity.n_participantes.toString() + " participantes"
                }
            } catch (e: Exception) {
                Log.e("ComunityActivity", "Erro ao seguir/desseguir comunidade", e)
            }
        }
    }

    fun requestParticipar(view: View) {
        if(message_participation.message == "Pedir"){
            lifecycleScope.launch {
                api_comunity.postRequestComunity(RequestParticipationComunityModel(user.id_user, comunity.id_comunity))
                requestParticipation()
            }
        }
    }


    fun seePublication(view: View) {
        closeAll()
        binding.btnPublications.setImageResource(R.drawable.poston)
        binding.rcFeed.visibility = View.VISIBLE
        binding.btnPublications.setBackgroundResource(R.drawable.button_border_red_selected)
    }

    fun seeAll(view: View) {
        closeAll()
        binding.boxRegras.visibility = View.VISIBLE
        binding.rcFeed.visibility = View.VISIBLE
        binding.btnAll.setImageResource(R.drawable.comon)
        binding.btnAll.setBackgroundResource(R.drawable.button_border_red_selected)
    }

    fun showEvents(view: View) {
        closeAll()
        binding.rcEvents.visibility = View.VISIBLE
        binding.btnEvents.setImageResource(R.drawable.eventoon)
        binding.btnEvents.setBackgroundResource(R.drawable.button_border_red_selected)
        lifecycleScope.launch {
            try {
                var list_events = api_events.eventMiniGet()
                val adapter = AdapterEvent(this@ComunityActivity, user, list_events)
                val rc = binding.rcEvents
                rc.layoutManager = LinearLayoutManager(this@ComunityActivity)
                rc.setHasFixedSize(true)
                rc.adapter = adapter

            }
            catch (e: Exception) { }
        }
    }
    fun seeGalery(view: View)
    {
        closeAll()
        binding.rcFeed.visibility = View.VISIBLE
        binding.btnGalery.setImageResource(R.drawable.galeriaon)
        binding.btnGalery.setBackgroundResource(R.drawable.button_border_red_selected)
        lifecycleScope.launch {
            try {
                list_publication = api_publication.publicationsGetComunity(comunity.id_comunity)
                val adapter = AdapterPublication(list_publication, this@ComunityActivity, user)
                val rc = binding.rcFeed
                rc.layoutManager = LinearLayoutManager(this@ComunityActivity)
                rc.setHasFixedSize(true)
                rc.adapter = adapter
            }
            catch (e: Exception) { }
        }
    }

    fun seeCalendar(view: View) {
        closeAll()

        binding.btnCalendar.setImageResource(R.drawable.calon)
        binding.btnCalendar.setBackgroundResource(R.drawable.button_border_red_selected)

    }
    fun closeAll(){
        binding.btnPublications.setImageResource(R.drawable.postred)
        binding.btnAll.setImageResource(R.drawable.comred)
        binding.btnEvents.setImageResource(R.drawable.eventored)
        binding.btnGalery.setImageResource(R.drawable.galeriared)
        binding.btnCalendar.setImageResource(R.drawable.calred)
        binding.btnCalendar.setBackgroundResource(R.color.transparentMesm)
        binding.btnPublications.setBackgroundResource(R.color.transparentMesm)
        binding.btnAll.setBackgroundResource(R.drawable.com)
        binding.btnEvents.setBackgroundResource(R.color.transparentMesm)
        binding.btnGalery.setBackgroundResource(R.color.transparentMesm)
        binding.boxRegras.visibility = View.GONE
        binding.rcFeed.visibility = View.GONE
        binding.rcEvents.visibility = View.GONE

    }




}