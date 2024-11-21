package devsystem.olimpiclink.ui.pages

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import devsystem.olimpiclink.R
import devsystem.olimpiclink.databinding.ActivitySelectCategoryBinding
import devsystem.olimpiclink.model.User
import devsystem.olimpiclink.model.UserModelPost
import devsystem.olimpiclink.model.util.ApiCliente
import devsystem.olimpiclink.model.util.EndpointUser
import kotlinx.coroutines.launch

class SelectCategoryActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySelectCategoryBinding
    private var list_selected = mutableListOf("natacao")
    private lateinit var user: User
    private lateinit var password_user: String
    private lateinit var api_user : EndpointUser
    private lateinit var city : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySelectCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        user = intent.extras!!.getParcelable("user")!!
        city =  intent.extras!!.getString("city")!!
        password_user = intent.extras!!.getString("password_user")!!
        api_user = ApiCliente.retrofit.create(EndpointUser::class.java)
    }

    fun clickNat(view: View) {
        if(list_selected.contains("natacao")){
            list_selected.remove("natacao")
            binding.imgNat.setBackgroundResource(R.color.transparentMesm)
        }
        else{
            list_selected.add("natacao")
            binding.imgNat.setBackgroundResource(R.drawable.logo_shape)
        }
    }

    fun clickBox(view: View) {
        if(list_selected.contains("Box")){
            list_selected.remove("Box")
            binding.imgBox.setBackgroundResource(R.color.transparentMesm)
        }
        else{
            list_selected.add("Box")
            binding.imgBox.setBackgroundResource(R.drawable.logo_shape)
        }
    }
    fun clickChess(view: View) {
        if(list_selected.contains("Xadrez")){
            list_selected.remove("Xadrez")
            binding.imgXadrez.setBackgroundResource(R.color.transparentMesm)
        }
        else{
            list_selected.add("Xadrez")
            binding.imgXadrez.setBackgroundResource(R.drawable.logo_shape)
        }
    }
    fun clickVolei(view: View) {
        if(list_selected.contains("Volei")){
            list_selected.remove("Volei")
            binding.imgVolei.setBackgroundResource(R.color.transparentMesm)
        }
        else{
            list_selected.add("Volei")
            binding.imgVolei.setBackgroundResource(R.drawable.logo_shape)
        }
    }
    fun clickBasquete(view: View) {
        if(list_selected.contains("Basquete")){
            list_selected.remove("Basquete")
            binding.imgBasquete.setBackgroundResource(R.color.transparentMesm)
        }
        else{
            list_selected.add("Basquete")
            binding.imgBasquete.setBackgroundResource(R.drawable.logo_shape)
        }
    }
    fun clickUsFoot(view: View) {
        if(list_selected.contains("UsFoot")){
            list_selected.remove("UsFoot")
            binding.imgUsfoot.setBackgroundResource(R.color.transparentMesm)
        }
        else{
            list_selected.add("UsFoot")
            binding.imgUsfoot.setBackgroundResource(R.drawable.logo_shape)
        }
    }

    fun register(view: View) {
        if(list_selected.size < 4){
            Toast.makeText(this, "Selecione ao menos 4 categorias", Toast.LENGTH_SHORT).show()
        }
        else{
            lifecycleScope.launch {
                try {
                    api_user.postUser(UserModelPost(user.name_user, user.email_user, password_user, user.login_user, city, list_selected))
                    val telaLogin = Intent(this@SelectCategoryActivity, InitialActivity::class.java)
                    startActivity(telaLogin)
                    finish()
                }
                catch (e:Exception){
                    Log.d("teste", e.toString())
                }
            }
        }
    }
}