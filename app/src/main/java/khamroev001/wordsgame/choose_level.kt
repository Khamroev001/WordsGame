package khamroev001.wordsgame

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_choose_level.*
import kotlin.system.exitProcess

class choose_level : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_level)
        allcoins.text=getSharedPreferences("coin", MODE_PRIVATE).getInt("coin",0).toString()
        choose_level1.setOnClickListener {
            var intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        choose_level2.setOnClickListener {
            var intent=Intent(this,level2::class.java)
            startActivity(intent)
        }
        choose_level3.setOnClickListener {
            var intent=Intent(this,level3::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}