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
    var galery = false;
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
//        val list = listOf(card1, card2)
//        val adapter = AdapterCommunityCard(this, list)
//        binding.profileMain.binding.rc1.layoutManager = LinearLayoutManager(this)
//        binding.profileMain.binding.rc1.setHasFixedSize(true)
//        binding.profileMain.binding.rc1.adapter = adapter
//
//        binding.profileMain.binding.rc2.layoutManager = LinearLayoutManager(this)
//        binding.profileMain.binding.rc2.setHasFixedSize(true)
//        binding.profileMain.binding.rc2.adapter = adapter
        publicationGet()
        categoriesGet()
        commonEvents.goPageCreationPublication(user, this, binding.bottomAppbarCustom.binding.btnPgCreatePublication)
        commonEvents.goPageMain(user, this, binding.bottomAppbarCustom.binding.btnPgInitial)
        commonEvents.goPageMyProfile(user, this, binding.bottomAppbarCustom.binding.btnPgProfile)
        commonEvents.goPageComunity(user, this, binding.bottomAppbarCustom.binding.btnPgCommunities)
        binding.bottomAppbarCustom.binding.btnPgProfile.setImageResource(R.drawable.profile_on)
    }

    private fun categoriesGet() {
        lifecycleScope.launch {
            try {
                var list_categories = api_user.getCategoriesUser(user.id_user)
                Glide.with(this@MyProfileActivity).load(list_categories[0]).into(binding.profileMain.binding.btnFilter8)
                Glide.with(this@MyProfileActivity).load(list_categories[1]).into(binding.profileMain.binding.btnFilter9)
                Glide.with(this@MyProfileActivity).load(list_categories[2]).into(binding.profileMain.binding.btnFilter10)
                Glide.with(this@MyProfileActivity).load(list_categories[3]).into(binding.profileMain.binding.btnFilter11)
            }
            catch (e : Exception){}
        }
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
                if(!galery){
                    list_publication = api_publication.publicationsGetID(user.id_user)
                }
                else{
                    list_publication = api_publication.publicationsGetIDGalery(user.id_user)
                }
                val adapter = AdapterPublication(list_publication, this@MyProfileActivity, user)
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
        binding.btnFilter5.setImageResource(R.drawable.poston)
        binding.btnFilter5.setBackgroundResource(R.drawable.button_border_red_selected)

        binding.btnFilter4.setImageResource(R.drawable.userred)
        binding.btnFilter4.setBackgroundResource(R.color.transparentMesm)
        binding.btnFilter6.setImageResource(R.drawable.galeriared)
        binding.btnFilter6.setBackgroundResource(R.color.transparentMesm)
        galery = false;
        publicationGet()
    }

    fun seeMainProfile(view: View) {
        binding.profileMain.visibility = View.VISIBLE
        binding.btnFilter5.setImageResource(R.drawable.postred)
        binding.btnFilter5.setBackgroundResource(R.color.transparentMesm)
        binding.btnFilter6.setImageResource(R.drawable.galeriared)
        binding.btnFilter6.setBackgroundResource(R.color.transparentMesm)

        binding.btnFilter4.setImageResource(R.drawable.profile_on)
        binding.btnFilter4.setBackgroundResource(R.drawable.button_border_red_selected)
        galery = false;
        publicationGet()
    }
    fun seeGaleryProfile(view: View) {
        binding.profileMain.visibility = View.GONE
        binding.btnFilter5.setImageResource(R.drawable.postred)
        binding.btnFilter5.setBackgroundResource(R.color.transparentMesm)
        binding.btnFilter4.setImageResource(R.drawable.userred)
        binding.btnFilter4.setBackgroundResource(R.color.transparentMesm)

        binding.btnFilter6.setImageResource(R.drawable.galeriaon)
        binding.btnFilter6.setBackgroundResource(R.drawable.button_border_red_selected)
        galery = true;
        publicationGet()
    }

    fun goToFollowers(view: View) {
        var main_activity = Intent(this, FollowersActivity::class.java)
        main_activity.putExtra("user", user)
        startActivity(main_activity)
    }

    fun goToFollows(view: View) {
        var main_activity = Intent(this, FollowsActivity::class.java)
        main_activity.putExtra("user", user)
        startActivity(main_activity)
    }

    fun goToFriends(view: View) {
        var main_activity = Intent(this, FriendsActivity::class.java)
        main_activity.putExtra("user", user)
        startActivity(main_activity)
    }
}