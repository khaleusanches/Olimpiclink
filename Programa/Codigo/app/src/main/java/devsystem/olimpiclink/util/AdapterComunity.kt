package devsystem.olimpiclink.util

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import devsystem.olimpiclink.databinding.ComunityComponentBinding
import devsystem.olimpiclink.databinding.ImagesPublicationBinding
import devsystem.olimpiclink.model.ComunityModel
import devsystem.olimpiclink.model.User
import devsystem.olimpiclink.model.util.EndpointUser
import devsystem.olimpiclink.ui.pages.ComunityActivity
import devsystem.olimpiclink.ui.pages.ComunityLeader
import devsystem.olimpiclink.ui.pages.PublishedEventActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class AdapterComunity(
    var context : Context,
    var user : User,
    var lista_images : List<ComunityModel>,
    private val api_comunity : EndpointComunity,
    private val adapterScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

) : RecyclerView.Adapter<AdapterComunity.ComunityCardViewHolder>()
{
    lateinit var teste : List<String>
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
            Log.d("teste", "teste")
            adapterScope.launch {
                try {
                    Log.d("adapterscope", "foi")
                    teste = api_comunity.isLeader(user.id_user, lista_images[position].id_comunity)
                    var main = Intent(context, ComunityActivity::class.java)
                    Log.d("teste", "teste")
                    if (teste.isNotEmpty()){
                        main = Intent(context, ComunityLeader::class.java)
                        main.putExtra("leader_id", teste[0].toInt())
                        Log.d("teste", "teste")
                    }
                    Log.d("teste", "teste")
                    main.putExtra("comunity_id",lista_images[position].id_comunity)
                    Log.d("teste", "teste")
                    main.putExtra("user", user)
                    context.startActivity(main)
                }
                catch (e:Exception){}
            }
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