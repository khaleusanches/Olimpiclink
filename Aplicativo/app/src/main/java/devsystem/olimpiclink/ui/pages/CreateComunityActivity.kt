package devsystem.olimpiclink.ui.pages

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import devsystem.olimpiclink.R
import devsystem.olimpiclink.databinding.ActivityCreateComunityBinding
import devsystem.olimpiclink.model.ComunityModelPost
import devsystem.olimpiclink.model.User
import devsystem.olimpiclink.model.util.ApiCliente
import devsystem.olimpiclink.util.CommonEvents
import devsystem.olimpiclink.util.EndpointComunity
import kotlinx.coroutines.launch

class CreateComunityActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCreateComunityBinding
    private lateinit var user : User
    private var selectImage : Uri? = null;
    private var category : Int? = null
    private var lista_imagens = mutableListOf<Uri?>()
    var tipos_selecao_imagem = 1
    var commonEvents = CommonEvents()

    private lateinit var api_comunity : EndpointComunity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityCreateComunityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        window.navigationBarColor = resources.getColor(R.color.laranja_splash)
        window.statusBarColor = ContextCompat.getColor(this, R.color.laranja_splash)
        user = intent.extras!!.getParcelable("user")!!
        Glide.with(this).load(R.drawable.redgroud).circleCrop().into(binding.imgComunityIcon)
        api_comunity = ApiCliente.retrofit.create(EndpointComunity::class.java)

        componentsInitialize()
        binding.etSobre.addTextChangedListener(object : TextWatcher {
            private var isDeleting = false

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                isDeleting = after < count
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null && s.length > 280 && !isDeleting) {
                    binding.etSobre.setText(s.subSequence(0, 280))
                    binding.etSobre.setSelection(280)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.etComunityName.addTextChangedListener(object : TextWatcher {
            private var isDeleting = false

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                isDeleting = after < count
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null && s.length > 45 && !isDeleting) {
                    binding.etSobre.setText(s.subSequence(0, 45))
                    binding.etSobre.setSelection(45)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

    }

    private fun componentsInitialize() {
        commonEvents.goPageCreationPublication(user, this, binding.bottomAppbarCustom.binding.btnPgCreatePublication)
        commonEvents.goPageMain(user, this, binding.bottomAppbarCustom.binding.btnPgInitial)
        commonEvents.goPageMyProfile(user, this, binding.bottomAppbarCustom.binding.btnPgProfile)
        commonEvents.goPageComunity(user, this, binding.bottomAppbarCustom.binding.btnPgCommunities)
        commonEvents.goPageSearch(user, this, binding.bottomAppbarCustom.binding.btnPgSearch)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 0){
            selectImage = data?.data
            if(tipos_selecao_imagem == 1){
                Glide.with(this).load(selectImage).into(binding.imgBanner)
            }
            else if(tipos_selecao_imagem == 2){
                Glide.with(this).load(selectImage).circleCrop().into(binding.imgComunityIcon)
            }
        }
    }

    fun adicionarBanner(view: View) {
        tipos_selecao_imagem = 1;
        var intent = Intent(Intent.ACTION_PICK);
        intent.setType("image/*")
        startActivityForResult(intent, requestCode = 0)
    }

    fun adicionarIcone(view: View) {
        tipos_selecao_imagem = 2;
        var intent = Intent(Intent.ACTION_PICK);
        intent.setType("image/*")
        startActivityForResult(intent, requestCode = 0)
    }
    fun padronizar(){
        binding.LLCorda.setBackgroundResource(R.drawable.button_border_red)
        binding.LLFoot.setBackgroundResource(R.drawable.button_border_red)
        binding.LLBox.setBackgroundResource(R.drawable.button_border_red)
        binding.LLVolei.setBackgroundResource(R.drawable.button_border_red)
        binding.LLXadrez.setBackgroundResource(R.drawable.button_border_red)
        binding.LLNatacao.setBackgroundResource(R.drawable.button_border_red)
        binding.LLUsfoot.setBackgroundResource(R.drawable.button_border_red)
        binding.LLBaseball.setBackgroundResource(R.drawable.button_border_red)
        binding.LLBasquete.setBackgroundResource(R.drawable.button_border_red)
    }
    fun selectBox(view: View) {
        category = 6
        padronizar()
        view.setBackgroundResource(R.drawable.button_border_laranja_selected)
    }
    fun selectBasquete(view: View) {
        category = 9
        padronizar()
        view.setBackgroundResource(R.drawable.button_border_laranja_selected)
    }
    fun selectCorda(view: View) {
        category = 11
        padronizar()
        view.setBackgroundResource(R.drawable.button_border_laranja_selected)
    }

    fun selectXadrez(view: View) {
        category = 7
        padronizar()
        view.setBackgroundResource(R.drawable.button_border_laranja_selected)
    }
    fun selectVolei(view: View) {
        category = 8
        padronizar()
        view.setBackgroundResource(R.drawable.button_border_laranja_selected)
    }
    fun selectUsFoot(view: View) {
        category = 10
        padronizar()
        view.setBackgroundResource(R.drawable.button_border_laranja_selected)
    }

    fun selectBaseball(view: View) {
        category = 13
        padronizar()
        view.setBackgroundResource(R.drawable.button_border_laranja_selected)
    }

    fun selectNatacao(view: View) {
        category = 5
        padronizar()
        view.setBackgroundResource(R.drawable.button_border_laranja_selected)
    }
    fun selectFoot(view: View) {
        category = 12
        padronizar()
        view.setBackgroundResource(R.drawable.button_border_laranja_selected)
    }

    fun createComunity(view: View) {
        if(binding.etComunityName.text.isNotEmpty() && binding.etSobre.text.isNotEmpty() && binding.etRegras.text.isNotEmpty()){
            var img_icon = commonEvents.byteArrayToBase64(commonEvents.imageToByte(binding.imgComunityIcon))
            Log.d("testee", img_icon!!)
            var img_banner = commonEvents.byteArrayToBase64(commonEvents.imageToByte(binding.imgBanner))
            if(category != null && img_icon != null && img_banner != null){
                var new_comunity = ComunityModelPost(
                    binding.etComunityName.text.toString(),
                    binding.etSobre.text.toString(),
                    category!!, binding.etRegras.text.toString(),
                    "$img_icon=", "$img_banner=", user.id_user
                )
                lifecycleScope.launch {
                    api_comunity.postComunity(new_comunity)
                    var main_activity = Intent(this@CreateComunityActivity, ListCommunitiesActivity::class.java)
                    main_activity.putExtra("user", user)
                    startActivity(main_activity)
                    finish()
                }

            }
        }

    }
}