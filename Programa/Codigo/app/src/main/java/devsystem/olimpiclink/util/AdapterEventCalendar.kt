package devsystem.olimpiclink.util

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import devsystem.olimpiclink.databinding.EventCalendarBinding
import devsystem.olimpiclink.model.EventModelGet
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AdapterEventCalendar (
    var context : Context,
    var lista_events : List<EventModelGet>
) : RecyclerView.Adapter<AdapterEventCalendar.EventCalendarViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventCalendarViewHolder {
        val event_item = EventCalendarBinding.inflate(LayoutInflater.from(context), parent, false)
        return EventCalendarViewHolder(event_item)
    }

    override fun getItemCount() = lista_events.size

    override fun onBindViewHolder(holder: EventCalendarViewHolder, position: Int) {
        holder.tv_title.text = lista_events[position].nameEvent
        if(lista_events[position].url_picture_event.isNotEmpty()){
            Glide.with(context).load(lista_events[position].url_picture_event[0]).into(holder.img_event_cape)
        }
        else{
            Glide.with(context).load("https://www.pontoxtecidos.com.br/static/567/sku/155904889535944.jpg").into(holder.img_event_cape)
        }
        holder.tv_comunity_name.text = lista_events[position].comunity_name
        holder.description.text = lista_events[position].descriptionEvent
        holder.tv_number.text = getDayFromDate(lista_events[position].dateTimeEvent)
    }

    fun getDayFromDate(dateTimeString: String): String {
        val formatter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss") // Formato de entrada
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        // Parse a string para um objeto LocalDateTime
        val dateTime = LocalDateTime.parse(dateTimeString, formatter)
        // Retorna apenas o dia como String
        return dateTime.dayOfMonth.toString()
    }

    inner class EventCalendarViewHolder(binding : EventCalendarBinding) : RecyclerView.ViewHolder(binding.root){
        var tv_number = binding.tvDia
        var img_event_cape = binding.imgEventCape
        var tv_title = binding.tvTitle
        var tv_comunity_name = binding.tvComunityName
        var description = binding.textView10
    }
}