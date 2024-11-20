package devsystem.olimpiclink.ui.pages

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import devsystem.olimpiclink.R
import devsystem.olimpiclink.databinding.ActivityMyProfileBinding
import devsystem.olimpiclink.databinding.ActivityOuterProfileBinding
import devsystem.olimpiclink.model.CommunityCardModel
import devsystem.olimpiclink.model.FriendsFollowsFollowersModel
import devsystem.olimpiclink.model.PublicationModelGet
import devsystem.olimpiclink.model.RequestMessages
import devsystem.olimpiclink.model.User
import devsystem.olimpiclink.model.UserFollowModel
import devsystem.olimpiclink.model.util.ApiCliente
import devsystem.olimpiclink.model.util.EndpointUser
import devsystem.olimpiclink.util.AdapterCommunityCard
import devsystem.olimpiclink.util.AdapterPublication
import devsystem.olimpiclink.util.CommonEvents
import devsystem.olimpiclink.util.EndpointPublication
import kotlinx.coroutines.launch

class OuterProfileActivity : AppCompatActivity() {
    private lateinit var binding : ActivityOuterProfileBinding
    private var id_user : Int = 1
    private lateinit var user_visited : User
    private lateinit var user : User
    private lateinit var api_user : EndpointUser
    private lateinit var api_publication : EndpointPublication
    private lateinit var fffm : FriendsFollowsFollowersModel
    private lateinit var list_publication : List<PublicationModelGet>
    private lateinit var message : RequestMessages
    private var commonEvents = CommonEvents()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityOuterProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        window.navigationBarColor = resources.getColor(R.color.laranja_splash)
        window.statusBarColor = ContextCompat.getColor(this, R.color.laranja_splash)
        id_user = intent.extras!!.getInt("id_user")
        user = intent.extras!!.getParcelable("user")!!
        Log.d("teste", id_user.toString())
        api_user = ApiCliente.retrofit.create(EndpointUser::class.java)
        api_publication = ApiCliente.retrofit.create(EndpointPublication::class.java)

        componentsInitialize()

        binding.profileMain.binding.textView8.text = "Categorias que o usuário salvou"
        binding.profileMain.binding.textView3.text = "Comunidades que o usuário está seguindo"
        binding.profileMain.binding.textView2.text = "Comunidades em que o usuário está"
    }

    private fun componentsInitialize() {
        Log.d("teste", "sim")
        getUser {

            binding.tvNameUser.text = user_visited.name_user
            binding.tvLoginUser.text = "@" + user_visited.login_user
            Glide.with(this@OuterProfileActivity).load(user_visited.url_profile_picture_user).circleCrop().into(binding.imgProfilePicture)
            commonEvents.goPageMain(user, this@OuterProfileActivity, binding.bottomAppbarCustom.binding.btnPgInitial)
            commonEvents.goPageCreationPublication(user, this@OuterProfileActivity, binding.bottomAppbarCustom.binding.btnPgCreatePublication)
            commonEvents.goPageMyProfile(user, this@OuterProfileActivity, binding.bottomAppbarCustom.binding.btnPgProfile)
            binding.bottomAppbarCustom.binding.btnPgProfile.setImageResource(R.drawable.profile_on)

            getFriendsFollowsFollowers()
            publicationGet()
            getFollowInterrogation{}
        }
        Glide.with(this).load("https://img.freepik.com/fotos-gratis/linda-nuvem-abstrata-e-ceu-azul-claro-paisagem-natureza-fundo-branco-e-papel-de-parede-azul-tex_1258-108688.jpg").into(binding.imgBanner)
        val card1 = CommunityCardModel("sim")
        val card2 = CommunityCardModel("nao")
        val list = listOf(card1, card2)
        val adapter = AdapterCommunityCard(this, list)
        binding.profileMain.binding.rc1.layoutManager = LinearLayoutManager(this)
        binding.profileMain.binding.rc1.setHasFixedSize(true)
        binding.profileMain.binding.rc1.adapter = adapter

        binding.profileMain.binding.rc2.layoutManager = LinearLayoutManager(this)
        binding.profileMain.binding.rc2.setHasFixedSize(true)
        binding.profileMain.binding.rc2.adapter = adapter
    }

    private fun getFollowInterrogation(onMessageLoaded: () -> Unit) {
        lifecycleScope.launch {
            try {
                message = api_user.getFollowInterrogation(user.id_user, id_user)
                attBtn()
                onMessageLoaded()
            }
            catch (e : Exception){
                Log.d("MyProfile", "errorrr")
            }
        }
    }

    private fun attBtn(){
        if(message.message == "amigos"){
            binding.FFF.text = "Amigos"
        }
        else if(message.message == "seguidor"){
            binding.FFF.text = "Seguir de volta"
        }
        else if(message.message == "seguindo"){
            binding.FFF.text = "Seguindo"
        }
        else {
            binding.FFF.text = "Seguir"
        }
    }
    private fun getUser(onUserLoaded: () -> Unit) {
        lifecycleScope.launch {
            try {
                Log.d("teste", "snao")
                user_visited = api_user.getUserID(id_user)
                onUserLoaded()
            }
            catch (e : Exception){
                Log.d("MyProfile", "errorrr")
            }
        }
    }
    private fun getFriendsFollowsFollowers() {
        lifecycleScope.launch {
            try {
                fffm = api_user.getFriendsFollowsFollowers(user_visited.id_user)
                binding.tvFriends.text = fffm.friends.toString() + " amigos"
                binding.tvFollows.text = fffm.follows.toString() + " seguindo"
                binding.tvFollowers.text = fffm.followers.toString() + " seguidores"
            }
            catch (e : Exception){
                Log.d("MyProfile", "error")
            }
        }
    }
    private fun publicationGet() {
        Log.d("MainActivity", "dentro")
        lifecycleScope.launch {
            Log.d("MainActivity", "launch")
            try {
                Log.d("MainActivity", "launch1")
                list_publication = api_publication.publicationsGetID(user_visited.id_user)
                val adapter = AdapterPublication(list_publication, this@OuterProfileActivity)
                val rc = binding.rcFeed
                rc.layoutManager = LinearLayoutManager(this@OuterProfileActivity, LinearLayoutManager.VERTICAL, false)
                rc.setHasFixedSize(true)
                rc.adapter = adapter

            }
            catch (e: Exception) {
                Log.d("MainActivity", "launch2")
                e.printStackTrace()
            }
        }
    }

    fun seePublications(view: View) {
        binding.profileMain.visibility = View.GONE
    }

    fun seeMainProfile(view: View) {
        binding.profileMain.visibility = View.VISIBLE
    }

    fun goToFollowers(view: View) {
        var main_activity = Intent(this, FollowersActivity::class.java)
        main_activity.putExtra("user_visited", user_visited)
        main_activity.putExtra("user", user)
        startActivity(main_activity)
    }

    fun goToFollows(view: View) {
        var main_activity = Intent(this, FollowsActivity::class.java)
        main_activity.putExtra("user_visited", user_visited)
        main_activity.putExtra("user", user)
        startActivity(main_activity)
    }

    fun goToFriends(view: View) {
        var main_activity = Intent(this, FriendsActivity::class.java)
        main_activity.putExtra("user_visited", user_visited)
        main_activity.putExtra("user", user)
        startActivity(main_activity)
    }

    fun seguirDeixarAmigos(view: View) {
        getFollowInterrogation{
            if(message.message == "amigos"){
                lifecycleScope.launch {
                    api_user.deleteFriend(user.id_user, user_visited.id_user)
                    getFriendsFollowsFollowers()
                    getFollowInterrogation{attBtn()}
                }
            }
            else if(message.message == "seguidor"){
                lifecycleScope.launch {
                    api_user.seguirUser(UserFollowModel(0, user.id_user, user_visited.id_user))
                    getFriendsFollowsFollowers()
                    getFollowInterrogation{attBtn()}
                }
            }
            else if(message.message == "seguindo"){
                lifecycleScope.launch {
                    api_user.deleteFollow(user.id_user, user_visited.id_user)
                    getFriendsFollowsFollowers()
                    getFollowInterrogation{attBtn()}
                    Log.d("uai", "uai1")
                }
            }
            else {
                Log.d("entrei", "entrado")
                lifecycleScope.launch {
                    api_user.seguirUser(UserFollowModel(0, user.id_user, user_visited.id_user))
                    getFriendsFollowsFollowers()
                    getFollowInterrogation{attBtn()}
                    Log.d("uai", "uai")
                }
            }
        }
        getFriendsFollowsFollowers()
    }
}