package khamroev001.wordsgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.widget.AppCompatButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener {
 var str:String=""
 var count=0
    var clickcount=0
 var word=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        reloadQuiz()
        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)

    }
    var quizArray= arrayOf(WordQuiz("rice","c","i","r","e"),
        WordQuiz("text","e","t","t","x"),
        WordQuiz("frog","o","r","g","f"),
        WordQuiz("earn","a","e","r","n"),
        WordQuiz("baby","b","y","a","b"),
        WordQuiz("neck","e","n","k","c"),
        WordQuiz("race","c","a","r","e"),
        WordQuiz("face","c","a","f","e"),
    )

    fun reloadQuiz(){
        restart()
      button1.text=quizArray[count].firstletter
      button2.text=quizArray[count].secondletter
      button3.text=quizArray[count].thirdletter
      button4.text=quizArray[count].fourthletter
        word=quizArray[count].word
        count++
    }
    override fun onClick(p0: View?) {
        var btn=findViewById<AppCompatButton>(p0!!.id)
        btn.isClickable=false
        clickcount++
        str+=btn.text
        if (clickcount==4){
            clickcount=0
            reloadQuiz()
        }
    }

    fun restart(){
        clickcount=0
        button1.isClickable=true
        button2.isClickable=true
        button3.isClickable=true
        button4.isClickable=true
    }
}