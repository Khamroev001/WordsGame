package khamroev001.wordsgame

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import kotlinx.android.synthetic.main.activity_level3.*
import kotlinx.android.synthetic.main.activity_level3.*
import kotlinx.android.synthetic.main.activity_level3.button1
import kotlinx.android.synthetic.main.activity_level3.button2
import kotlinx.android.synthetic.main.activity_level3.button3
import kotlinx.android.synthetic.main.activity_level3.button4
import kotlinx.android.synthetic.main.activity_level3.button5
import kotlinx.android.synthetic.main.activity_level3.button6
import kotlinx.android.synthetic.main.activity_level3.cube1
import kotlinx.android.synthetic.main.activity_level3.cube2
import kotlinx.android.synthetic.main.activity_level3.cube3
import kotlinx.android.synthetic.main.activity_level3.cube4
import kotlinx.android.synthetic.main.activity_level3.cube5
import kotlinx.android.synthetic.main.activity_level3.cube6
import kotlinx.android.synthetic.main.activity_level3.tv

class level3 : AppCompatActivity(), View.OnClickListener {
    var str: String = ""
    var count = 0
    var clickcount = 0
    var correctanswer: Array<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level3)

        reloadQuiz()
        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        button5.setOnClickListener(this)
        button6.setOnClickListener(this)


        object : CountDownTimer(30000, 1000) {

            // Callback function, fired on regular interval
            override fun onTick(millisUntilFinished: Long) {
                tv.setText((millisUntilFinished / 1000).toString())
            }

            // Callback function, fired
            // when the time is up
            override fun onFinish() {

            }
        }.start()

    }

    var quizArray= arrayOf(
        WordQuiz_level3(arrayOf("answer"),"w","r","e","n","a","s"),
        WordQuiz_level3(arrayOf("amount"),"u","t","m","n","a","o"),
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
        button5.text = quizArray[count].fifthletter
        button6.text = quizArray[count].sixthletter
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
        if (clickcount.toString() == "5") {
            cuberepressed(cube5, btn)
        }
        if (clickcount.toString() == "6") {
            cuberepressed(cube6, btn)
        }
        if (clickcount == 6 && check()) {
            clickcount = 0
            Handler(Looper.getMainLooper()).postDelayed({
                reloadQuiz()
            }, 300)
        } else if (clickcount == 6 && !check()) {
            Handler(Looper.getMainLooper()).postDelayed({
                restart()
            }, 300)
        }
    }

    fun check(): Boolean {
        for (i in  0..correctanswer?.size!! -1){
            if (str == correctanswer!![i]) {
                return true
            }
        }

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
    fun buttonrestart(button: TextView){
        button.setTextColor(Color.BLACK)
        button.setBackgroundResource(R.drawable.btn_bcg_white)
    }

    fun restart() {
        str = ""
        clickcount = 0

        buttonrestart(button1)
        buttonrestart(button2)
        buttonrestart(button3)
        buttonrestart(button4)
        buttonrestart(button5)
        buttonrestart(button6)



        cuberestart(cube1)
        cuberestart(cube2)
        cuberestart(cube3)
        cuberestart(cube4)
        cuberestart(cube5)
        cuberestart(cube6)


        button1.isClickable = true
        button2.isClickable = true
        button3.isClickable = true
        button4.isClickable = true
        button5.isClickable = true
        button6.isClickable = true
    }



}