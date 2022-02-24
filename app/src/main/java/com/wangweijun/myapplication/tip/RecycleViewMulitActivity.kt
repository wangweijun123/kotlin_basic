package com.wangweijun.myapplication.tip

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.wangweijun.myapplication.R
import com.wangweijun.myapplication.adapter.CustomAdapter
import com.wangweijun.myapplication.adapter.CustomAdapterUseBing
import com.wangweijun.myapplication.adapter.MultiAdapterJava
import com.wangweijun.myapplication.databinding.ActivityRecycleViewBinding
import com.wangweijun.myapplication.databinding.ActivityTipTimeBinding
import java.text.NumberFormat

/**
 * RecyclerView
 *   ViewHolder: ViewHolder 是 View 的封装容器
 * RecyclerView.Adapter  数据
 * 布局管理器 LayoutManager
 * @property binding ActivityRecycleViewBinding
 */
class RecycleViewMulitActivity : AppCompatActivity(){
    private lateinit var binding: ActivityRecycleViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecycleViewBinding.inflate(LayoutInflater.from(applicationContext))
        setContentView(binding.root)
        // recyclerview 已经在xml中设置了layoutmanager布局管理器
        val arrayList = arrayListOf<String>()
        for (i in 1..60) {
            arrayList.add("i=$i")
        }
        val customAdapter = MultiAdapterJava(applicationContext, arrayList)

        binding.recyclerView.adapter = customAdapter
    }




}