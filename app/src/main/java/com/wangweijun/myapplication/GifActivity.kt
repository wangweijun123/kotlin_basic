package com.wangweijun.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide

class GifActivity : AppCompatActivity(){
    lateinit var iv: ImageView
    lateinit var lottieAnimationView: LottieAnimationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_gif)

        iv = findViewById<ImageView>(R.id.iv)
        lottieAnimationView = findViewById(R.id.animation_view)
        lottieAnimationView.playAnimation()
    }

    fun startGif(view: View) {
        Glide.with(this)
//            .load(R.raw.animation_500_l4duynqv)
            .load(R.raw.animation_200_l4eb92ca)
            .into(iv);
    }

    fun startLottieGif(view: View) {
        if (lottieAnimationView.isAnimating) {
            lottieAnimationView.pauseAnimation()
        } else {
            lottieAnimationView.resumeAnimation()
        }
    }

    fun startOtherActivity(view: View) {
        startActivity(Intent(applicationContext, CoroutineActivity::class.java))
    }

    override fun onResume() {
        super.onResume()
        lottieAnimationView.resumeAnimation()
    }

    override fun onPause() {
        super.onPause()
        lottieAnimationView.pauseAnimation()
    }
}