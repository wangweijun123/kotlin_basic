package com.wangweijun.testdeeplink

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var lateTV: TextView
    private lateinit var name: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (this::name.isInitialized) {

        }

    }

    fun jumpDeepLink(view: android.view.View) {
        val intent = Intent()
        intent.setAction("android.intent.action.VIEW")
        intent.addCategory("android.intent.category.DEFAULT")
        intent.addCategory("android.intent.category.BROWSABLE")
        intent.setData(Uri.parse("example://gizmos"))
        startActivity(intent)

    }

    fun jumpBNDeepLink(view: android.view.View) {
        val intent = Intent()
        intent.setAction("android.intent.action.VIEW")
        intent.addCategory("android.intent.category.DEFAULT")
        intent.addCategory("android.intent.category.BROWSABLE")
        intent.setData(Uri.parse("bnc://app.binance.com/account/facePlusPlus?faceTransID=FL:user:b92d7939854c450786256550e0626ec4"))
        intent.putExtra("sceneValue", 1011)
        startActivity(intent)

    }
}