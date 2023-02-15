package khamroev001.wordsgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


    }

    override fun onStart() {
        super.onStart()
        var intent=Intent(this,choose_level::class.java)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(intent)
            finish()
        }, 4000)
    }
}