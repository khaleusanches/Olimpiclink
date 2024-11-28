package devsystem.olimpiclink.ui.pages

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import devsystem.olimpiclink.R
import devsystem.olimpiclink.databinding.ActivityPublishedEventBinding
import devsystem.olimpiclink.model.EventModelGet
import devsystem.olimpiclink.model.User
import devsystem.olimpiclink.model.util.ApiCliente
import devsystem.olimpiclink.util.CommonEvents
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import devsystem.olimpiclink.util.EndpointEvent
import kotlinx.coroutines.launch

class PublishedEventActivity : AppCompatActivity() {
    private lateinit var user : User
    private lateinit var binding : ActivityPublishedEventBinding
    private var event_id = 0
    private lateinit var event : EventModelGet
    private var commonEvents = CommonEvents()
    //api
    private lateinit var api_event : EndpointEvent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPublishedEventBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        window.navigationBarColor = resources.getColor(R.color.laranja_splash)
        window.statusBarColor = ContextCompat.getColor(this, R.color.laranja_splash)

        api_event = ApiCliente.retrofit.create(EndpointEvent::class.java)
        user = intent.extras!!.getParcelable("user")!!
        event_id = intent.extras!!.getInt("event_id")

        commonEvents.goPageCreationPublication(user, this, binding.bottomMenu.binding.btnPgCreatePublication)
        commonEvents.goPageMain(user, this, binding.bottomMenu.binding.btnPgInitial)
        commonEvents.goPageMyProfile(user, this, binding.bottomMenu.binding.btnPgProfile)
        commonEvents.goPageComunity(user, this, binding.bottomMenu.binding.btnPgCommunities)
        commonEvents.goPageSearch(user, this, binding.bottomMenu.binding.btnPgSearch)
        loadEvent()
    }

    private fun loadEvent() {
        lifecycleScope.launch {
            event = api_event.eventGet(event_id)
            binding.tvEventTitle.text = event.nameEvent
            binding.tvDescriptionEvent.text = event.descriptionEvent
            binding.tvComunityName.text = event.comunity_name
            Glide.with(this@PublishedEventActivity).load(event.comunity_picture).circleCrop().into(binding.imgPictureProfileUser)
            if(event.url_picture_event.isNotEmpty()){
                Glide.with(this@PublishedEventActivity).load(event.url_picture_event[0]).into(binding.imgEventCape)
            }else{
                binding.imgEventCape.visibility = View.GONE
            }
            binding.tvDate.text = "De " + formatDateTime(event.dateTimeEvent) + " até " + formatDateTime(event.closingDateTimeEvent) + "no endereço: " + event.endereco
        }
    }

    fun formatDateTime(dateTimeString: String): String {
        // Defina o padrão correto, sem o 'o'
        val formatter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        } else {
            TODO("VERSION.SDK_INT < O")
        } // Ajuste para seu formato de data
        val dateTime = LocalDateTime.parse(dateTimeString, formatter)

        // Se quiser formatar a data, use um novo padrão
        val outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy, 'às' HH:mm 'horas'")
        return dateTime.format(outputFormatter)
    }

    fun goToComunity(view: View) {
        var main_activity = Intent(this, ComunityActivity::class.java)
        view.setBackgroundResource(R.drawable.edit_text_selected)
        main_activity.putExtra("user", user)
        main_activity.putExtra("comunity_id", event.comunity_id)
        startActivity(main_activity)
        finish()
    }
}