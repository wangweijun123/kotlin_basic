package com.wangweijun.testdeeplink

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
        intent.setData(Uri.parse("bnc://app.binance.com/webview/webview?type=default&url=aHR0cHM6Ly93d3cuZGV2ZmRnLm5ldC9qYS90ZXJtcw&needLogin=false"))
        startActivity(intent)

    }
}