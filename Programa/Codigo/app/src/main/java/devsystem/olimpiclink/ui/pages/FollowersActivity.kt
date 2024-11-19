package devsystem.olimpiclink.ui.pages

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import devsystem.olimpiclink.R
import devsystem.olimpiclink.databinding.ActivityFollowersBinding
import devsystem.olimpiclink.model.FriendsFollowsFollowersScreenModel
import devsystem.olimpiclink.model.User
import devsystem.olimpiclink.model.util.ApiCliente
import devsystem.olimpiclink.model.util.EndpointUser
import devsystem.olimpiclink.util.AdapterFriendsFollowsFollowers
import devsystem.olimpiclink.util.CommonEvents
import kotlinx.coroutines.launch

class FollowersActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFollowersBinding
    private lateinit var api_users : EndpointUser
    private lateinit var user : User
    private lateinit var list_followers : MutableList<FriendsFollowsFollowersScreenModel>
    var commonEvents = CommonEvents()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFollowersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        window.navigationBarColor = resources.getColor(R.color.laranja_splash)
        window.statusBarColor = ContextCompat.getColor(this, R.color.laranja_splash)
        api_users = ApiCliente.retrofit.create(EndpointUser::class.java)
        user = intent.extras!!.getParcelable<User>("user")!!
        getFollowers()


        commonEvents.goPageMain(user, this, binding.bottomAppbarCustom.binding.btnPgInitial)
        commonEvents.goPageCreationPublication(user, this, binding.bottomAppbarCustom.binding.btnPgCreatePublication)
        commonEvents.goPageMyProfile(user, this, binding.bottomAppbarCustom.binding.btnPgProfile)
    }

    fun getFollowers(){
        lifecycleScope.launch {
            try {
                list_followers = api_users.getFollowers(user.id_user)
                val adapter = AdapterFriendsFollowsFollowers(this@FollowersActivity, list_followers, this, user.id_user, api_users)
                binding.rcFollowers.layoutManager = LinearLayoutManager(this@FollowersActivity)
                binding.rcFollowers.setHasFixedSize(true)
                binding.rcFollowers.adapter = adapter
            }
            catch (e : Exception){
                Log.d("FollowersActivity", "Erro")
            }
        }
    }
}