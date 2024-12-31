package devsystem.olimpiclink.ui.pages

import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
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
import com.applandeo.materialcalendarview.CalendarDay
import com.applandeo.materialcalendarview.listeners.OnCalendarDayClickListener
import com.applandeo.materialcalendarview.listeners.OnCalendarPageChangeListener
import com.bumptech.glide.Glide
import devsystem.olimpiclink.R
import devsystem.olimpiclink.databinding.ActivityComunityBinding
import devsystem.olimpiclink.databinding.ActivityComunityLeaderBinding
import devsystem.olimpiclink.model.ComunityModel
import devsystem.olimpiclink.model.EventModelGet
import devsystem.olimpiclink.model.FollowComunityModel
import devsystem.olimpiclink.model.PublicationModelGet
import devsystem.olimpiclink.model.RequestMessages
import devsystem.olimpiclink.model.RequestParticipationComunityModel
import devsystem.olimpiclink.model.User
import devsystem.olimpiclink.model.util.ApiCliente
import devsystem.olimpiclink.util.AdapterEvent
import devsystem.olimpiclink.util.AdapterEventCalendar
import devsystem.olimpiclink.util.AdapterPublication
import devsystem.olimpiclink.util.CommonEvents
import devsystem.olimpiclink.util.EndpointComunity
import devsystem.olimpiclink.util.EndpointEvent
import devsystem.olimpiclink.util.EndpointPublication
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ComunityLeader : AppCompatActivity() {
    private lateinit var binding : ActivityComunityLeaderBinding
    private lateinit var user : User
    private var comunity_id = 0
    private var leader_id = 0
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
        binding = ActivityComunityLeaderBinding.inflate(layoutInflater)
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
        leader_id = intent.extras!!.getInt("leader_id")

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

        }
        commonEvents.goPageCreationPublication(user, this, binding.bottomAppbarCustom.binding.btnPgCreatePublication)
        commonEvents.goPageMain(user, this, binding.bottomAppbarCustom.binding.btnPgInitial)
        commonEvents.goPageMyProfile(user, this, binding.bottomAppbarCustom.binding.btnPgProfile)
        commonEvents.goPageComunity(user, this, binding.bottomAppbarCustom.binding.btnPgCommunities)
        commonEvents.goPageSearch(user, this, binding.bottomAppbarCustom.binding.btnPgSearch)


        binding.calendarViewteste.setOnPreviousPageChangeListener(object :
            OnCalendarPageChangeListener {
            override fun onChange() {
                seeCalendar(binding.calendarViewteste)
            }
        })
        binding.calendarViewteste.setOnForwardPageChangeListener(object :
            OnCalendarPageChangeListener {
            override fun onChange() {
                seeCalendar(binding.calendarViewteste)
            }
        })
        binding.calendarViewteste.setOnCalendarDayClickListener(object :
            OnCalendarDayClickListener {
            override fun onClick(calendarDay: CalendarDay) {
                val day = String.format("%02d", calendarDay.calendar.get(Calendar.DAY_OF_MONTH))
                val month = String.format("%02d", calendarDay.calendar.get(Calendar.MONTH))
                if(calendarDay.imageResource == R.drawable.calred){
                    var lista : ArrayList<Int> = arrayListOf(comunity_id, day.toInt(), month.toInt())
                    var more_see = Intent(this@ComunityLeader, MoreSeeActivity::class.java)
                    more_see.putExtra("user", user)
                    more_see.putExtra("recomended", true)
                    more_see.putExtra("data", lista)
                    startActivity(more_see)
                }
            }
        })
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
                val adapter = AdapterPublication(list_publication, this@ComunityLeader, user)
                val rc = binding.rcFeed
                rc.layoutManager = LinearLayoutManager(this@ComunityLeader)
                rc.setHasFixedSize(true)
                rc.adapter = adapter

            }
            catch (e: Exception) {
                Log.d("MainActivity", "launch2")
                e.printStackTrace()
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
                var list_events = api_events.eventGetComunity(comunity_id)
                val adapter = AdapterEvent(this@ComunityLeader, user, list_events)
                val rc = binding.rcEvents
                rc.layoutManager = LinearLayoutManager(this@ComunityLeader)
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
                val adapter = AdapterPublication(list_publication, this@ComunityLeader, user)
                val rc = binding.rcFeed
                rc.layoutManager = LinearLayoutManager(this@ComunityLeader)
                rc.setHasFixedSize(true)
                rc.adapter = adapter
            }
            catch (e: Exception) { }
        }
    }

    fun seeCalendar(view: View) {
        closeAll()
        binding.calendarViewteste.visibility = View.VISIBLE
        binding.btnCriarEvento.visibility = View.VISIBLE
        binding.rcCalendarEvents.visibility = View.VISIBLE
        binding.btnCalendar.setImageResource(R.drawable.calon)
        binding.btnCalendar.setBackgroundResource(R.drawable.button_border_red_selected)

        var dates = mutableListOf("1900/12/11T19:22:10")
        var list_event : List<EventModelGet>
        var calendars : ArrayList<CalendarDay> = ArrayList()
        lifecycleScope.launch {
            try {
                list_event = api_events.eventGetComunityCalendar(comunity.id_comunity, binding.calendarViewteste.currentPageDate.get(
                    Calendar.MONTH)+1)
                val adapter = AdapterEventCalendar(this@ComunityLeader, list_event)
                val rc = binding.rcCalendarEvents
                rc.layoutManager = LinearLayoutManager(this@ComunityLeader)
                rc.setHasFixedSize(true)
                rc.adapter = adapter

                list_event.forEach{
                    dates.add(it.dateTimeEvent)
                    Log.d("teste", it.dateTimeEvent)
                    val data = getDayFromDate(it.dateTimeEvent)
                    val cal = java.util.Calendar.getInstance()
                    cal.set(data[2], data[1], data[0])
                    val calDay = CalendarDay(cal)
                    calDay.labelColor = R.color.red
                    calDay.imageResource = R.drawable.calred
                    calendars.add(calDay)
                }
                binding.calendarViewteste.setCalendarDays(calendars)
            }
            catch (e: Exception) { }
        }
    }

    fun getDayFromDate(dateTimeString: String): List<Int> {
        val formatter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss") // Formato de entrada
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        // Parse a string para um objeto LocalDateTime
        val dateTime = LocalDateTime.parse(dateTimeString, formatter)
        // Retorna apenas o dia como String
        return listOf(dateTime.dayOfMonth, dateTime.monthValue-1, dateTime.year)
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
        binding.calendarViewteste.visibility = View.GONE
        binding.rcFeed.visibility = View.GONE
        binding.rcEvents.visibility = View.GONE
        binding.rcCalendarEvents.visibility = View.GONE
        binding.btnCriarEvento.visibility = View.GONE

    }

    fun createEvent(view: View) {
        var main_activity = Intent(this, UnpublishedEventActivity::class.java)
        main_activity.putExtra("comunity_id",comunity_id)
        main_activity.putExtra("leader_id", leader_id)
        main_activity.putExtra("user", user)
        startActivity(main_activity)

    }
}