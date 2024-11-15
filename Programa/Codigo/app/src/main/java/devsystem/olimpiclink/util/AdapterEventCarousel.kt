package devsystem.olimpiclink.util

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import devsystem.olimpiclink.databinding.CarouselItemBinding
import devsystem.olimpiclink.model.Carousel

class AdapterEventCarousel(
    private val context: Context,
    private val listImages: MutableList<Carousel>
) : RecyclerView.Adapter<AdapterEventCarousel.CarouselViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val itemList = CarouselItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return CarouselViewHolder(itemList)
    }

    override fun getItemCount(): Int {
        return listImages.size
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        Glide.with(context)
            .load(listImages[position])
            .into(holder.imgPhotoEvent)
    }

    inner class CarouselViewHolder(binding: CarouselItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val imgPhotoEvent = binding.imgEventPublication
    }
}
