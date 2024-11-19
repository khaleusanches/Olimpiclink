package devsystem.olimpiclink.ui.pages

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import devsystem.olimpiclink.R
import devsystem.olimpiclink.databinding.ActivityFriendsBinding
import devsystem.olimpiclink.model.FriendsFollowsFollowersScreenModel
import devsystem.olimpiclink.model.User
import devsystem.olimpiclink.model.util.ApiCliente
import devsystem.olimpiclink.model.util.EndpointUser
import devsystem.olimpiclink.util.AdapterFriendsFollowsFollowers
import devsystem.olimpiclink.util.CommonEvents
import kotlinx.coroutines.launch

class FriendsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFriendsBinding
    private lateinit var api_users : EndpointUser
    private lateinit var user : User
    private lateinit var list_friends : MutableList<FriendsFollowsFollowersScreenModel>
    var commonEvents = CommonEvents()
    private var search = false;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFriendsBinding.inflate(layoutInflater)
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
        binding.etSearchUser.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0.isNullOrEmpty()){
                    search = false;
                }
                else{
                    search = true;
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                TODO("Not yet implemented")
            }
        })

        commonEvents.goPageMain(user, this, binding.bottomAppbarCustom.binding.btnPgInitial)
        commonEvents.goPageCreationPublication(user, this, binding.bottomAppbarCustom.binding.btnPgCreatePublication)
        commonEvents.goPageMyProfile(user, this, binding.bottomAppbarCustom.binding.btnPgProfile)
    }

    fun getFollowers(){
        lifecycleScope.launch {
            try {
                if(search == false){
                    list_friends = api_users.getFollowers(user.id_user)
                }
                else {
                    list_friends = api_users.getFollowers(user.id_user)
                }
                val adapter = AdapterFriendsFollowsFollowers(this@FriendsActivity, list_friends, this, user.id_user, api_users)
                binding.rcFriends.layoutManager = LinearLayoutManager(this@FriendsActivity)
                binding.rcFriends.setHasFixedSize(true)
                binding.rcFriends.adapter = adapter
            }
            catch (e : Exception){
                Log.d("FollowersActivity", "Erro")
            }
        }
    }
}