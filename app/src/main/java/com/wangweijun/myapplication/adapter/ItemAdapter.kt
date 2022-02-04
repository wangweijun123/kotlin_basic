package com.wangweijun.myapplication.adapter

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wangweijun.myapplication.R
import com.wangweijun.myapplication.model.Affirmation

class ItemAdapter(private val context: Context, private val dataSet: List<Affirmation>) {

    /**
     * ViewHolder 只有view引用的Holder
     */
    class ItemViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.item_title)
    }
}