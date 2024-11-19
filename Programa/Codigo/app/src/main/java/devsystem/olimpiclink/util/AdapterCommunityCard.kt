package devsystem.olimpiclink.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import devsystem.olimpiclink.databinding.CommunityCardBinding
import devsystem.olimpiclink.databinding.EventPublishedMiniBinding
import devsystem.olimpiclink.model.CommunityCardModel
import devsystem.olimpiclink.model.EventMiniModelGet
import devsystem.olimpiclink.ui.CommunityCard
import devsystem.olimpiclink.util.AdapterEventMini.EventMiniViewHolder

class AdapterCommunityCard(val context: Context, private val list_community_cardModel: List<CommunityCardModel>)
    : RecyclerView.Adapter<AdapterCommunityCard.CommunityCardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityCardViewHolder {
        val itemLista = CommunityCardBinding.inflate(LayoutInflater.from(context), parent, false)
        return CommunityCardViewHolder(itemLista)
    }

    override fun getItemCount() = list_community_cardModel.size

    //Exibe
    override fun onBindViewHolder(holder: CommunityCardViewHolder, position: Int) {
        holder.community_name.text = list_community_cardModel[position].teste
    }
    inner class CommunityCardViewHolder(binding : CommunityCardBinding) : RecyclerView.ViewHolder(binding.root){
        val community_name = binding.tvCommunityName
        val community_bio = binding.tvBio
    }
}