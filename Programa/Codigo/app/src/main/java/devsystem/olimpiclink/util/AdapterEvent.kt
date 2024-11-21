package devsystem.olimpiclink.util

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import devsystem.olimpiclink.databinding.EventPublishedBinding
import devsystem.olimpiclink.databinding.EventPublishedMiniBinding
import devsystem.olimpiclink.model.EventMiniModelGet
import devsystem.olimpiclink.model.User
import devsystem.olimpiclink.ui.EventPublishedMini
import devsystem.olimpiclink.ui.pages.PublishedEventActivity

class AdapterEvent(val context: Context, private val user : User, private val listEvents: List<EventMiniModelGet>):
    RecyclerView.Adapter<AdapterEvent.EventViewHolder>() {

    //Cria
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val itemLista = EventPublishedBinding.inflate(LayoutInflater.from(context), parent, false)
        return EventViewHolder(itemLista)
    }

    override fun getItemCount() = listEvents.size

    //Exibe
    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.event_title.text = listEvents[position].nameEvent
        holder.comunity_name.text = listEvents[position].comunity_name
        holder.description_event.text = listEvents[position].descriptionEvent
        Glide.with(context).load(listEvents[position].comunity_picture).circleCrop().into(holder.comunity_picture)
        if(listEvents[position].url_picture_event.size > 0){
            Glide.with(context).load(listEvents[position].url_picture_event[0]).into(holder.event_cape)
        }
        else {
            holder.event_cape.visibility = View.GONE
        }
        holder.event.setOnClickListener(View.OnClickListener {
            var main_activity = Intent(context, PublishedEventActivity::class.java)
            main_activity.putExtra("event_id",listEvents[position].idEvent)
            main_activity.putExtra("user", user)
            context.startActivity(main_activity)
        })
    }

    inner class EventViewHolder(binding: EventPublishedBinding): RecyclerView.ViewHolder(binding.root) {
        val event_title = binding.tvEventTitle
        val comunity_name = binding.tvComunityName
        val comunity_picture = binding.imgComunityPicture
        val description_event = binding.tvDescriptionEvent
        val event_cape = binding.imgEventCape
        val date_publication = binding.tvDatePublication
        val event = binding.event
    }
}