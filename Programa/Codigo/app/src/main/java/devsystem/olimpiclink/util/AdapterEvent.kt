package devsystem.olimpiclink.util

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import devsystem.olimpiclink.databinding.CarouselItemBinding
import devsystem.olimpiclink.model.Carousel

class AdapterEvent(
    val context: Context,
    val listImages: MutableList<Carousel>
) : RecyclerView.Adapter<AdapterEvent.CarouselViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val itemList = CarouselItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return CarouselViewHolder(itemList)
    }

    override fun getItemCount(): Int {
        return listImages.size
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        val currentItem = listImages[position]

        currentItem.ImageUri?.let {
            Glide.with(context)
                .load(it)
                .into(holder.ImgPhotoEvent)
        }
    }
    inner class CarouselViewHolder(binding: CarouselItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val ImgPhotoEvent = binding.imgEventPublication
    }
}
