package devsystem.olimpiclink.util

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import androidx.lifecycle.lifecycleScope
import devsystem.olimpiclink.databinding.FriendsFollowsFollowersBinding
import devsystem.olimpiclink.model.FriendsFollowsFollowersScreenModel
import devsystem.olimpiclink.model.util.EndpointUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AdapterFriendsFollowsFollowers(
    val context: Context,
    val listFriendsFollowsFollowers: MutableList<FriendsFollowsFollowersScreenModel>,
    private val lifecycleScope: CoroutineScope,
    private val id_user : Int,
    private val api_users : EndpointUser,
    private val adapterScope: CoroutineScope = CoroutineScope(Dispatchers.Main + Job())

) : RecyclerView.Adapter<AdapterFriendsFollowsFollowers.FriendsFollowsFollowersViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FriendsFollowsFollowersViewHolder {
        val itemLista = FriendsFollowsFollowersBinding.inflate(LayoutInflater.from(context), parent, false)
        return FriendsFollowsFollowersViewHolder(itemLista)
    }

    override fun getItemCount() = listFriendsFollowsFollowers.size

    override fun onBindViewHolder(holder: FriendsFollowsFollowersViewHolder, position: Int) {
        Glide.with(context).load(listFriendsFollowsFollowers[position].img_profile).circleCrop().into(holder.FFF_img_user)
        holder.FFF_login_user.text = listFriendsFollowsFollowers[position].login_user
        holder.FFF_login_user.setOnClickListener(View.OnClickListener {
            Log.d("Pinto", listFriendsFollowsFollowers[position].login_user)
        })

        holder.FFF_btn.setOnClickListener(View.OnClickListener {
            adapterScope.launch{
                try{
                    api_users.deleteFollower(id_user, listFriendsFollowsFollowers[position].user_id)
                    listFriendsFollowsFollowers.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, listFriendsFollowsFollowers.size)
                }
                catch (e : Exception){
                }
            }
        })
    }

    inner class FriendsFollowsFollowersViewHolder(binding: FriendsFollowsFollowersBinding): RecyclerView.ViewHolder(binding.root){
        val FFF_img_user = binding.imgPictureProfileUser
        val FFF_login_user = binding.tvLoginUser
        val FFF_btn = binding.btnRemove
    }
}