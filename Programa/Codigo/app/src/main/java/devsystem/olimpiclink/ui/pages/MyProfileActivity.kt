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
import devsystem.olimpiclink.model.CommunityCardModel
import devsystem.olimpiclink.model.FriendsFollowsFollowersModel
import devsystem.olimpiclink.model.PublicationModelGet
import devsystem.olimpiclink.model.User
import devsystem.olimpiclink.model.util.ApiCliente
import devsystem.olimpiclink.model.util.EndpointUser
import devsystem.olimpiclink.util.AdapterCommunityCard
import devsystem.olimpiclink.util.AdapterPublication
import devsystem.olimpiclink.util.CommonEvents
import devsystem.olimpiclink.util.EndpointPublication
import kotlinx.coroutines.launch

class MyProfileActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMyProfileBinding
    private lateinit var user : User
    private lateinit var api_user : EndpointUser
    private lateinit var api_publication : EndpointPublication
    private lateinit var fffm : FriendsFollowsFollowersModel
    private lateinit var list_publication : List<PublicationModelGet>
    private var commonEvents = CommonEvents()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMyProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        window.navigationBarColor = resources.getColor(R.color.laranja_splash)
        window.statusBarColor = ContextCompat.getColor(this, R.color.laranja_splash)
        user = intent.extras!!.getParcelable<User>("user")!!
        api_user = ApiCliente.retrofit.create(EndpointUser::class.java)
        api_publication = ApiCliente.retrofit.create(EndpointPublication::class.java)
        componentsInitialize()
    }

    private fun componentsInitialize() {
        Glide.with(this).load(user.url_profile_picture_user).circleCrop().into(binding.imgProfilePicture)
        binding.tvNameUser.text = user.name_user
        binding.tvLoginUser.text = "@" + user.login_user
        Glide.with(this).load("https://img.freepik.com/fotos-gratis/linda-nuvem-abstrata-e-ceu-azul-claro-paisagem-natureza-fundo-branco-e-papel-de-parede-azul-tex_1258-108688.jpg").into(binding.imgBanner)

        getFriendsFollowsFollowers()
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
        publicationGet()

        commonEvents.goPageMain(user, this, binding.bottomAppbarCustom.binding.btnPgInitial)
        commonEvents.goPageCreationPublication(user, this, binding.bottomAppbarCustom.binding.btnPgCreatePublication)
        binding.bottomAppbarCustom.binding.btnPgProfile.setImageResource(R.drawable.profile_on)
    }

    private fun getFriendsFollowsFollowers() {
        lifecycleScope.launch {
            try {
                fffm = api_user.getFriendsFollowsFollowers(user.id_user)
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
                list_publication = api_publication.publicationsGetID(user.id_user)
                val adapter = AdapterPublication(list_publication, this@MyProfileActivity)
                val rc = binding.rcFeed
                rc.layoutManager = LinearLayoutManager(this@MyProfileActivity, LinearLayoutManager.VERTICAL, false)
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
        main_activity.putExtra("user", user)
        startActivity(main_activity)
    }
}