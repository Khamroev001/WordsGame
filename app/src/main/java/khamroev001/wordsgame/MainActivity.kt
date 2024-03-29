package khamroev001.wordsgame

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.button1
import kotlinx.android.synthetic.main.activity_main.button2
import kotlinx.android.synthetic.main.activity_main.button3
import kotlinx.android.synthetic.main.activity_main.button4
import kotlinx.android.synthetic.main.activity_main.cube1
import kotlinx.android.synthetic.main.activity_main.cube2
import kotlinx.android.synthetic.main.activity_main.cube3
import kotlinx.android.synthetic.main.activity_main.cube4
import kotlinx.android.synthetic.main.dialog_view.view.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var str: String = ""
    var count = 0
    var clickcount = 0
    var score = 0
    var k = 0
    var coins=score
    var correctanswer: Array<String>? = null
    var sharedPreferences: SharedPreferences? =null
   lateinit var edit: SharedPreferences.Editor

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences=getSharedPreferences("coin", MODE_PRIVATE)
        edit= sharedPreferences?.edit()!!
        reloadQuiz()
        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        allcoins.text= sharedPreferences?.getInt("coin",0).toString()

        finish.setOnClickListener {
            val view = View.inflate(this@MainActivity, R.layout.dialog_view, null)
            view.score.text = "${score}/${k-1}"
            view.coin.text = "${score}"

            allcoins.text=(allcoins.text.toString().toInt()+view.coin.text.toString().toInt()).toString()
            savecoins(allcoins.text.toString().toInt())

            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setView(view)

            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setCancelable(false)

            view.home.setOnClickListener {
                dialog.dismiss()
                var intent = Intent(this, choose_level::class.java)
                startActivity(intent)
            }
            view.restart.setOnClickListener {
                dialog.dismiss()
                val intent = intent
                finish()
                startActivity(intent)
            }
        }

    }
    fun savecoins(coins:Int){
        edit?.putInt("coin",coins)
        edit?.apply()
    }

    var quizArray = arrayOf(
        WordQuiz(arrayOf("rice"), "c", "i", "r", "e"),
        WordQuiz(arrayOf("text"), "e", "t", "t", "x"),
        WordQuiz(arrayOf("frog"), "o", "r", "g", "f"),
        WordQuiz(arrayOf("earn", "near"), "a", "e", "r", "n"),
        WordQuiz(arrayOf("baby"), "b", "y", "a", "b"),
        WordQuiz(arrayOf("neck"), "e", "n", "k", "c"),
        WordQuiz(arrayOf("race", "care"), "c", "a", "r", "e"),
        WordQuiz(arrayOf("face"), "c", "a", "f", "e"),
        WordQuiz(arrayOf("love"), "o", "e", "l", "v"),
        WordQuiz(arrayOf("home"), "o", "e", "m", "h"),
        WordQuiz(arrayOf("book"), "o", "b", "k", "o"),
        WordQuiz(arrayOf("boat"), "a", "b", "t", "o"),
    )

    fun reloadQuiz() {
        if (count == quizArray.size) {
            count = 0
        }
        restart()
        button1.text = quizArray[count].firstletter
        button2.text = quizArray[count].secondletter
        button3.text = quizArray[count].thirdletter
        button4.text = quizArray[count].fourthletter
        correctanswer = quizArray[count].word
        count++
    }

    override fun onClick(p0: View?) {
        var btn = findViewById<TextView>(p0!!.id)

        btn.isClickable = false
        clickcount++
        str += btn.text
        btn.setTextColor(Color.WHITE)
        btn.setBackgroundResource(R.drawable.btn_bcg_blue)
        if (clickcount.toString() == "1") {
            cuberepressed(cube1, btn)
        }
        if (clickcount.toString() == "2") {
            cuberepressed(cube2, btn)
        }
        if (clickcount.toString() == "3") {
            cuberepressed(cube3, btn)
        }
        if (clickcount.toString() == "4") {
            cuberepressed(cube4, btn)
        }
        if (clickcount == 4 && check()) {
            clickcount = 0
            Handler(Looper.getMainLooper()).postDelayed({
                reloadQuiz()
            }, 300)
        } else if (clickcount == 4 && !check()) {
            Handler(Looper.getMainLooper()).postDelayed({
                restart()
            }, 300)
        }
    }

    fun check(): Boolean {
        for (i in 0..correctanswer?.size!! - 1) {
            if (str == correctanswer!![i]) {
                score++
                return true
            }
        }

//        var anim = AnimationUtils.loadAnimation(this, R.anim.anim_error_vibration)
//        cube1.startAnimation(anim)
//        cube2.startAnimation(anim)
//        cube3.startAnimation(anim)
//        cube4.startAnimation(anim)

        return false
    }

    fun cuberepressed(cube: TextView, btn: TextView) {
        cube.text = btn.text
        cube.setTextColor(Color.BLACK)
        cube.setBackgroundResource(R.drawable.cube_bcg_pressed)
    }

    fun cuberestart(cube: TextView) {
        cube.text = ""
        cube.setTextColor(Color.WHITE)
        cube.setBackgroundResource(R.drawable.outlined)
    }

    fun buttonrestart(button: TextView) {
        button.setTextColor(Color.BLACK)
        button.setBackgroundResource(R.drawable.btn_bcg_white)
        button.isClickable = true

    }

    fun restart() {
        k++
        str = ""
        clickcount = 0


        buttonrestart(button1)
        buttonrestart(button2)
        buttonrestart(button3)
        buttonrestart(button4)

        cube1.text = ""
        cube2.text = ""
        cube3.text = ""
        cube4.text = ""

        cuberestart(cube1)
        cuberestart(cube2)
        cuberestart(cube3)
        cuberestart(cube4)


        button1.isClickable = true
        button2.isClickable = true
        button3.isClickable = true
        button4.isClickable = true
    }


}