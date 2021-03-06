package com.example.kotlincatchthekenny

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.measureTimeMillis
import kotlin.random.Random as Random

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    var score=0
    var imageArray=ArrayList<ImageView>()
    var handler=Handler()
    var runnable= Runnable {  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageArray.add(imageView1)
        imageArray.add(imageView2)
        imageArray.add(imageView3)
        imageArray.add(imageView4)
        imageArray.add(imageView5)
        imageArray.add(imageView6)
        imageArray.add(imageView7)
        imageArray.add(imageView8)
        imageArray.add(imageView9)
        hideImage()

        object :CountDownTimer(10000,1000){
            override fun onFinish() {

                handler.removeCallbacks(runnable)
                for (image in imageArray){
                    image.visibility=View.INVISIBLE}

                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Play Again?")
                alert.setPositiveButton("Yes") { dialog, which ->
                val intent = intent
                finish()
                startActivity(intent)
            }


                alert.setNegativeButton("No") { dialog, which ->
                    Toast.makeText(this@MainActivity, "Game Over", Toast.LENGTH_SHORT).show()
                }
                alert.show()

            }


            override fun onTick(p0: Long) {
                timeText.text="Time: "+ p0/1000

            }

        }.start()
    }

    fun hideImage(){
        runnable=object :Runnable{
            override fun run() {
                for(image in imageArray){
                    image.visibility=View.INVISIBLE
                }

                val random=java.util.Random()
                val randomIndex=random.nextInt(9)
                imageArray[randomIndex].visibility=View.VISIBLE
                handler.postDelayed(runnable,800)

            }

        }
        handler.post(runnable)


    }




        fun increaseScore (view: View)

        {
            score++;
            scoreText.text="Score:$score"
    }

}