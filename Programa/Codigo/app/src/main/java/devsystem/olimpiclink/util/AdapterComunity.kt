package devsystem.olimpiclink.util

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import devsystem.olimpiclink.databinding.ComunityComponentBinding
import devsystem.olimpiclink.databinding.ImagesPublicationBinding
import devsystem.olimpiclink.model.ComunityModel
import devsystem.olimpiclink.model.User
import devsystem.olimpiclink.ui.pages.ComunityActivity
import devsystem.olimpiclink.ui.pages.PublishedEventActivity

class AdapterComunity(
    var context : Context,
    var user : User,
    var lista_images : List<ComunityModel>
) : RecyclerView.Adapter<AdapterComunity.ComunityCardViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComunityCardViewHolder {
        val componente = ComunityComponentBinding.inflate(LayoutInflater.from(context), parent, false)
        return ComunityCardViewHolder(componente)
    }

    override fun getItemCount() = lista_images.size

    override fun onBindViewHolder(holder: ComunityCardViewHolder, position: Int) {
        Glide.with(context).load("https://png.pngtree.com/thumb_back/fh260/background/20190223/ourmid/pngtree-color-tennis-sport-advertising-background-backgroundmotionwork-outtennistreeshand-paintedfreshhouses-image_75815.jpg").into(holder.imgBanner)
        Glide.with(context).load(lista_images[position].url_icon_comunity).circleCrop().into(holder.img_icon)
        holder.tv_bio.text = lista_images[position].description_comunity
        holder.tv_comunity_name.text = lista_images[position].name_comunity
        Glide.with(context).load(lista_images[position].category_icon).into(holder.img_categoria)
        holder.all.setOnClickListener(View.OnClickListener {
            var main_activity = Intent(context, ComunityActivity::class.java)
            main_activity.putExtra("comunity_id",lista_images[position].id_comunity)
            main_activity.putExtra("user", user)
            context.startActivity(main_activity)
        })
    }

    inner class ComunityCardViewHolder(binding : ComunityComponentBinding) : RecyclerView.ViewHolder(binding.root){
        val tv_comunity_name = binding.tvComunityName
        val imgBanner = binding.imgBanner
        val img_icon = binding.imgIconCommunity
        val tv_bio = binding.textView6
        val img_categoria = binding.imageView6
        val all = binding.CL1
    }
}