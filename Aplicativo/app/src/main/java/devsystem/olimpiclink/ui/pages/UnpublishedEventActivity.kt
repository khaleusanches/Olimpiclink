package devsystem.olimpiclink.ui.pages

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import devsystem.olimpiclink.R
import devsystem.olimpiclink.databinding.ActivityUnpublishedEventBinding
import devsystem.olimpiclink.model.PostEventModel
import devsystem.olimpiclink.model.User
import devsystem.olimpiclink.model.util.ApiCliente
import devsystem.olimpiclink.util.CommonEvents
import devsystem.olimpiclink.util.EndpointEvent
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
class UnpublishedEventActivity : AppCompatActivity() {
    private lateinit var binding : ActivityUnpublishedEventBinding
    private val calendar = Calendar.getInstance()
    private lateinit var api_event : EndpointEvent
    var comunity_id = 0
    private var selectImage : Uri? = null
    var leader_id = 0
    var commonEvents = CommonEvents()
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUnpublishedEventBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        api_event = ApiCliente.retrofit.create(EndpointEvent::class.java)
        comunity_id = intent.extras!!.getInt("comunity_id")
        leader_id = intent.extras!!.getInt("leader_id")
        user = intent.extras!!.getParcelable("user")!!

        commonEvents.goPageCreationPublication(user, this, binding.bottomAppbarCustom.binding.btnPgCreatePublication)
        commonEvents.goPageMain(user, this, binding.bottomAppbarCustom.binding.btnPgInitial)
        commonEvents.goPageMyProfile(user, this, binding.bottomAppbarCustom.binding.btnPgProfile)
        commonEvents.goPageComunity(user, this, binding.bottomAppbarCustom.binding.btnPgCommunities)
        commonEvents.goPageSearch(user, this, binding.bottomAppbarCustom.binding.btnPgSearch)
    }

    fun showDataInicio(view: View) {
        val dataPickerDialog = DatePickerDialog(this, { _, year: Int, monthOfYear: Int, dayOfMonth: Int ->
            calendar.set(year, monthOfYear, dayOfMonth)
            val timePickerDialog = TimePickerDialog(this, { _, hourOfDay: Int, minute: Int ->
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)

                val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                val formattedDateTime = dateFormat.format(calendar.time)

                binding.etInicio.text = Editable.Factory.getInstance().newEditable(formattedDateTime)
            },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true)
            timePickerDialog.show()
        },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        dataPickerDialog.show()
    }

    fun showDataFinish(view: View) {
        val dataPickerDialog = DatePickerDialog(this, { _, year: Int, monthOfYear: Int, dayOfMonth: Int ->
            calendar.set(year, monthOfYear, dayOfMonth)
            val timePickerDialog = TimePickerDialog(this, { _, hourOfDay: Int, minute: Int ->
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)

                val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                val formattedDateTime = dateFormat.format(calendar.time)

                binding.etFim.text = Editable.Factory.getInstance().newEditable(formattedDateTime)
            },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true)
            timePickerDialog.show()
        },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        dataPickerDialog.show()
    }

    fun adicionarBanner(view: View) {
        var intent = Intent(Intent.ACTION_PICK);
        intent.setType("image/*")
        startActivityForResult(intent, requestCode = 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 0){
            selectImage = data?.data
            if(selectImage != null){
                binding.imgBanner.visibility = View.VISIBLE
                Glide.with(this).load(selectImage).into(binding.imgBanner)
                val layoutParams = binding.etDescription.layoutParams as ConstraintLayout.LayoutParams
                layoutParams.topToBottom = R.id.img_banner
                binding.etDescription.layoutParams = layoutParams
                binding.main.requestLayout()
            }
        }
    }

    fun publicarEvent(view: View) {
        if(binding.etTitle.text.isNullOrEmpty() || binding.etDescription.text.isNullOrEmpty() || binding.etInicio.text.isNullOrEmpty() || binding.etFim.text.isNullOrEmpty() || binding.imgBanner.visibility == View.GONE){

        }
        else{
            var dateFormat = SimpleDateFormat("dd/MM/yyyy'T'HH:mm", Locale.getDefault())
            var img_banner = commonEvents.byteArrayToBase64(commonEvents.imageToByte(binding.imgBanner))
            if(img_banner != null){
                lifecycleScope.launch {
                    try {
                        var new_event = PostEventModel(
                                    2, comunity_id, leader_id, binding.etTitle.text.toString(), binding.etDescription.text.toString(),
                                    binding.etInicio.text.toString(),
                                    binding.etFim.text.toString(),
                                    arrayOf(img_banner), binding.etLocal.text.toString()
                                )
                        Log.d("teste", new_event.dateTimeEvent)
                        Log.d("teste", img_banner)
                        api_event.postEvent(new_event)

                        var main_activity = Intent(this@UnpublishedEventActivity, MainActivity::class.java)
                        main_activity.putExtra("user", user)
                        startActivity(main_activity)
                        finish()
                    }
                    catch (e:Exception){}
                }
            }
        }
    }
}