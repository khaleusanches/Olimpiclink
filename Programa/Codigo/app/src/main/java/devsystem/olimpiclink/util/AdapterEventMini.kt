package devsystem.olimpiclink.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import devsystem.olimpiclink.databinding.EventPublishedMiniBinding
import devsystem.olimpiclink.model.EventMiniModelGet
import devsystem.olimpiclink.ui.EventPublishedMini

class AdapterEventMini(val context: Context, private val listEventsMini: MutableList<EventMiniModelGet>):
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
    }

    inner class EventMiniViewHolder(binding: EventPublishedMiniBinding): RecyclerView.ViewHolder(binding.root) {
        val event_title = binding.tvEventTitle
        val comunity_name = binding.tvComunityName
        val comunity_picture = binding.imgComunityPicture
        val description_event = binding.tvDescriptionEvent
        val event_cape = binding.imgEventCape
        val date_publication = binding.tvDatePublication
    }
}