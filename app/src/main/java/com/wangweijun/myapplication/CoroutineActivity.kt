package com.wangweijun.myapplication

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.boycoder.kthttp.ApiServiceV3
import com.boycoder.kthttp.Callback
import com.boycoder.kthttp.KtHttpV3
import com.boycoder.kthttp.await
import com.wangweijun.myapplication.mycoroutines.last.getLastThreadInfo
import com.wangweijun.myapplication.zhangtao.unit11.annotations.RepoList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CoroutineActivity : AppCompatActivity(){
    lateinit var contnet: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_coroutine)
        contnet = findViewById<TextView>(R.id.mContent)

    }

    private fun getRepos() {
        lifecycleScope.launch {
            val start = System.currentTimeMillis()
            val ktCall = KtHttpV3.create(ApiServiceV3::class.java)
                .repos(lang = "Kotlin", since = "weekly")
            val result = ktCall.await() // 这里是异步代码，同步写
            println(result)
            contnet.text = result.toString()
            println("Time total: ${System.currentTimeMillis() - start}")
        }
    }

    fun useJavaThreadForNetwork(view: View) {
        KtHttpV3.create(ApiServiceV3::class.java).repos(
            lang = "Kotlin",
            since = "weekly"
        ).call(object : Callback<RepoList> {
            override fun onSuccess(data: RepoList) {
                println(data)
            }

            override fun onFail(throwable: Throwable) {
                println(throwable)
            }
        })
    }
    fun useCoroutineForNetwork(view: View) {
        getRepos()
    }




    fun clearText(view: View) {
        lifecycleScope.launch(Dispatchers.Main) {
            println("start ... ")
            val start = System.currentTimeMillis()
            delay(3000)
            println("spend time = ${System.currentTimeMillis() - start} ${getLastThreadInfo()}")
        }
    }
}