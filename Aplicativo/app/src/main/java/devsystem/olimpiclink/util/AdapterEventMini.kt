package devsystem.olimpiclink.util

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import devsystem.olimpiclink.databinding.EventPublishedMiniBinding
import devsystem.olimpiclink.model.EventMiniModelGet
import devsystem.olimpiclink.model.User
import devsystem.olimpiclink.ui.EventPublishedMini
import devsystem.olimpiclink.ui.pages.MyProfileActivity
import devsystem.olimpiclink.ui.pages.OuterProfileActivity
import devsystem.olimpiclink.ui.pages.PublishedEventActivity

class AdapterEventMini(val context: Context, private val user: User, private val listEventsMini: List<EventMiniModelGet>):
    RecyclerView.Adapter<AdapterEventMini.EventMiniViewHolder>() {

    //Cria
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventMiniViewHolder {
        val itemLista = EventPublishedMiniBinding.inflate(LayoutInflater.from(context), parent, false)
        return EventMiniViewHolder(itemLista)
    }

    override fun getItemCount() = listEventsMini.size

    //Exibe
    override fun onBindViewHolder(holder: EventMiniViewHolder, position: Int) {
        holder.event_title.text = listEventsMini[position].nameEvent
        holder.comunity_name.text = listEventsMini[position].comunity_name
        holder.description_event.text = listEventsMini[position].descriptionEvent
        Glide.with(context).load(listEventsMini[position].comunity_picture).circleCrop().into(holder.comunity_picture)
        if(listEventsMini[position].url_picture_event.isNotEmpty()){
            Glide.with(context).load(listEventsMini[position].url_picture_event[0]).into(holder.event_cape)
        }
        else{
            holder.event_cape.visibility = View.GONE
        }
        holder.event.setOnClickListener(View.OnClickListener {
            var main_activity = Intent(context, PublishedEventActivity::class.java)
            main_activity.putExtra("event_id",listEventsMini[position].idEvent)
            main_activity.putExtra("user", user)
            context.startActivity(main_activity)
        })
    }

    inner class EventMiniViewHolder(binding: EventPublishedMiniBinding): RecyclerView.ViewHolder(binding.root) {
        val event_title = binding.tvEventTitle
        val comunity_name = binding.tvComunityName
        val comunity_picture = binding.imgComunityPicture
        val description_event = binding.tvDescriptionEvent
        val event_cape = binding.imgEventCape
        val date_publication = binding.tvDatePublication
        val event = binding.event
    }
}