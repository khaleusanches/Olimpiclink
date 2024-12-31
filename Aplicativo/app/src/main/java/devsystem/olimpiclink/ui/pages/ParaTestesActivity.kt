package devsystem.olimpiclink.ui.pages

import android.content.res.Configuration
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.applandeo.materialcalendarview.CalendarDay
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.listeners.OnCalendarDayClickListener
import com.applandeo.materialcalendarview.listeners.OnCalendarPageChangeListener
import com.bumptech.glide.Glide
import devsystem.olimpiclink.R
import devsystem.olimpiclink.databinding.ActivityParaTestesBinding
import devsystem.olimpiclink.model.EventModelGet
import devsystem.olimpiclink.util.AdapterEventCalendar

class ParaTestesActivity : AppCompatActivity() {
    private lateinit var binding : ActivityParaTestesBinding
    private lateinit var calendarview : CalendarView
    private var events : MutableMap<String, String> = mutableMapOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityParaTestesBinding.inflate(layoutInflater)
        val screenSize = resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK

        when (screenSize) {
            Configuration.SCREENLAYOUT_SIZE_SMALL -> Log.d("ScreenSize", "Small screen detected")
            Configuration.SCREENLAYOUT_SIZE_NORMAL -> Log.d("ScreenSize", "Normal screen detected")
            Configuration.SCREENLAYOUT_SIZE_LARGE -> Log.d("ScreenSize", "Large screen detected")
            Configuration.SCREENLAYOUT_SIZE_XLARGE -> Log.d("ScreenSize", "XLarge screen detected")
            else -> Log.d("ScreenSize", "Unknown screen size")
        }
        val densityDpi = resources.displayMetrics.densityDpi

        when (densityDpi) {
            in 0..120 -> Log.d("ScreenDensity", "ldpi (Low density)")
            in 121..160 -> Log.d("ScreenDensity", "mdpi (Medium density)")
            in 161..240 -> Log.d("ScreenDensity", "hdpi (High density)")
            in 241..320 -> Log.d("ScreenDensity", "xhdpi (Extra-high density)")
            in 321..480 -> Log.d("ScreenDensity", "xxhdpi (Extra-extra-high density)")
            in 481..640 -> Log.d("ScreenDensity", "xxxhdpi (Extra-extra-extra-high density)")
            else -> Log.d("ScreenDensity", "Unknown density")
        }


        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Glide.with(this).load("http://192.168.0.158:5000/api/comunities/banner/8").into(binding.imgBanner)
        Glide.with(this).load("https://claraaraujo2.github.io/Olimpiclink/Programa/Design_e_Modelagem/telas/boxred.png").into(binding.btnCategory)
        Glide.with(this).load("https://proece.ufms.br/files/2021/05/Qdd_Geral-1024x1024.jpg").circleCrop().into(binding.imgComunityIcon)

        calendarview = binding.calendarViewteste

        val calendars : ArrayList<CalendarDay> = ArrayList()
        val calendar = java.util.Calendar.getInstance()
        calendar.set(2024,7,30)
        val calendarDay = CalendarDay(calendar)
        calendarDay.labelColor = R.color.red
        calendarDay.imageResource = R.drawable.calred
        calendars.add(calendarDay)
        events["20-07-2024"] = "BurguerDay"

        var datas : List<Int> = listOf(28, 27, 16, 11)
        datas.forEach {
            val cal = java.util.Calendar.getInstance()
            cal.set(2024, 10, it)
            val calDay = CalendarDay(cal)
            calDay.labelColor = R.color.red
            calDay.imageResource = R.drawable.calred
            calendars.add(calDay)
        }
        calendarview.setCalendarDays(calendars)

        calendarview.setOnCalendarDayClickListener(object : OnCalendarDayClickListener {
            override fun onClick(calendarDay: CalendarDay) {
                val day = String.format("%02d", calendarDay.calendar.get(Calendar.DAY_OF_MONTH))
                val month = String.format("%02d", calendarDay.calendar.get(Calendar.MONTH))
                val year = calendarDay.calendar.get(Calendar.YEAR)
                if(events.containsKey("$day-$month-$year")){
                    Log.d("pinto", "pintao")
                }
                else{
                    Log.d("normal", "$day-$month-$year")

                }
            }
        })
        calendarview.setOnPreviousPageChangeListener(object : OnCalendarPageChangeListener{
            override fun onChange() {
                Log.d("voltei", "pintao")
            }
        })
        calendarview.setOnForwardPageChangeListener(object : OnCalendarPageChangeListener{
            override fun onChange() {
                Log.d("voltei", "pintao")
            }
        })

        teste()
    }

    private fun teste() {

    }
}