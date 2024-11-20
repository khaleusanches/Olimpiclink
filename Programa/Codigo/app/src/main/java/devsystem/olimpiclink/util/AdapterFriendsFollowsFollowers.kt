package devsystem.olimpiclink.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import androidx.lifecycle.lifecycleScope
import devsystem.olimpiclink.databinding.FriendsFollowsFollowersBinding
import devsystem.olimpiclink.model.FriendsFollowsFollowersScreenModel
import devsystem.olimpiclink.model.User
import devsystem.olimpiclink.model.util.EndpointUser
import devsystem.olimpiclink.ui.pages.FriendsActivity
import devsystem.olimpiclink.ui.pages.MyProfileActivity
import devsystem.olimpiclink.ui.pages.OuterProfileActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AdapterFriendsFollowsFollowers(
    val context: Activity,
    val listFriendsFollowsFollowers: MutableList<FriendsFollowsFollowersScreenModel>,
    private val lifecycleScope: CoroutineScope,
    private val id_user : Int,
    private val api_users : EndpointUser,
    private val type : String,
    private val user: User,
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
            var main_activity = Intent(context, OuterProfileActivity::class.java)
            if(user.id_user == listFriendsFollowsFollowers[position].user_id){
                main_activity = Intent(context, MyProfileActivity::class.java)
            }
            main_activity.putExtra("id_user",listFriendsFollowsFollowers[position].user_id)
            main_activity.putExtra("user", user)
            context.startActivity(main_activity)
            context.finish()
        })

        holder.FFF_btn.setOnClickListener(View.OnClickListener {
            adapterScope.launch{
                try{
                    if(type == "FFF"){
                        api_users.deleteFollower(id_user, listFriendsFollowsFollowers[position].user_id)
                    }
                    else if (type == "FF"){
                        api_users.deleteFollow(id_user, listFriendsFollowsFollowers[position].user_id)
                    }
                    else if (type == "F"){
                        api_users.deleteFriend(id_user, listFriendsFollowsFollowers[position].user_id)
                    }
                    listFriendsFollowsFollowers.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, listFriendsFollowsFollowers.size)
                }
                catch (e : Exception){
                }
            }
        })
        if(user.id_user != id_user){
            holder.FFF_btn.visibility = View.GONE
        }
    }

    inner class FriendsFollowsFollowersViewHolder(binding: FriendsFollowsFollowersBinding): RecyclerView.ViewHolder(binding.root){
        val FFF_img_user = binding.imgPictureProfileUser
        val FFF_login_user = binding.tvLoginUser
        val FFF_btn = binding.btnRemove
    }
}