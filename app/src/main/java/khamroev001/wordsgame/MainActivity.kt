package khamroev001.wordsgame

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var str: String = ""
    var count = 0
    var clickcount = 0
    var correctanswer = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        reloadQuiz()
        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)

    }

    var quizArray = arrayOf(
        WordQuiz("rice", "c", "i", "r", "e"),
        WordQuiz("text", "e", "t", "t", "x"),
        WordQuiz("frog", "o", "r", "g", "f"),
        WordQuiz("earn", "a", "e", "r", "n"),
        WordQuiz("baby", "b", "y", "a", "b"),
        WordQuiz("neck", "e", "n", "k", "c"),
        WordQuiz("race", "c", "a", "r", "e"),
        WordQuiz("face", "c", "a", "f", "e"),
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
            restart()
        }
    }

    fun check(): Boolean {
        if (str == correctanswer) {
            return true
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

    fun restart() {
        str = ""
        clickcount = 0
        button1.setTextColor(Color.BLACK)
        button1.setBackgroundResource(R.drawable.btn_bcg_white)
        button2.setTextColor(Color.BLACK)
        button2.setBackgroundResource(R.drawable.btn_bcg_white)
        button3.setTextColor(Color.BLACK)
        button3.setBackgroundResource(R.drawable.btn_bcg_white)
        button4.setTextColor(Color.BLACK)
        button4.setBackgroundResource(R.drawable.btn_bcg_white)

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