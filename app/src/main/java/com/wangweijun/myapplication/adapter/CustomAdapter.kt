package com.wangweijun.myapplication.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wangweijun.myapplication.R

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/02/23 2:38 下午
 * version: 1.0
 * desc   :
 */
class CustomAdapter(private val context: Context, private val dataSet: ArrayList<String>) : RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {
    val tag = "duanxia"
    // ViewHolder 持有view，设置数据，叫绑定数据
    // 列表中的每一个元素都是一个viewholder，应为viewholder持有view, 注意viewholder重用
    // viewholder 是item View的封装容器
    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.item_title)
    }
    // 这里叫创建viewholder，注意并没有设置数据, 并不会填充视图，也就是说不会显示
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        val customViewHolder = CustomViewHolder(itemView)
        Log.i(tag, "onCreateViewHolder  viewholder = $customViewHolder")
        itemView.setOnClickListener {
            // 通过viewholder去拿位置
            Log.i(tag, "adapterPosition = ${customViewHolder.adapterPosition}")
        }
        return customViewHolder
    }
    // 一页显示5个的时候，到第9个才复用viewHolder,
    // 这是叫绑定数据，你要去设置数据
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        Log.i(tag, "onBindViewHolder position=$position,  holder = $holder")
        holder.textView.text = dataSet[position]
        if (position % 2 == 0) {
            holder.textView.setBackgroundColor(context.getColor(R.color.purple_200))
        } else {
            holder.textView.setBackgroundColor(context.getColor(R.color.teal_700))
        }
    }

    override fun getItemCount(): Int {
        Log.i(tag, "ogetItemCount = ${dataSet.size}")
        return dataSet.size
    }
}