package devsystem.olimpiclink.ui.pages

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import devsystem.olimpiclink.R

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private var TIME_OUT = 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        window.navigationBarColor = resources.getColor(R.color.laranja_splash)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf({Manifest.permission.WRITE_EXTERNAL_STORAGE}.toString()), 1);
        }
        timeIsUp()
    }

    private fun timeIsUp() {
        Handler().postDelayed({
            val telaLogin = Intent(this, InitialActivity::class.java)
            startActivity(telaLogin)
            finish()
        }, TIME_OUT.toLong())
    }
}