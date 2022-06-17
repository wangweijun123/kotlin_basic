package com.wangweijun.myapplication

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class GifActivity : AppCompatActivity(){
    lateinit var iv: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_gif)

        iv = findViewById<ImageView>(R.id.iv)
    }

    fun startGif(view: View) {
        Glide.with(this)
//            .load(R.raw.animation_500_l4duynqv)
            .load(R.raw.animation_200_l4eb92ca)
            .into(iv);
    }
}