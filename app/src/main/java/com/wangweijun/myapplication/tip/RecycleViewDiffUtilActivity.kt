package com.wangweijun.myapplication.tip

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import com.wangweijun.myapplication.R
import com.wangweijun.myapplication.adapter.CustomAdapter
import com.wangweijun.myapplication.adapter.CustomAdapterDiffUtil
import com.wangweijun.myapplication.adapter.CustomAdapterUseBing
import com.wangweijun.myapplication.adapter.MyModel
import com.wangweijun.myapplication.databinding.ActivityRecycleViewBinding
import com.wangweijun.myapplication.databinding.ActivityRecycleViewDiffUtilBinding
import com.wangweijun.myapplication.databinding.ActivityTipTimeBinding
import java.text.NumberFormat

/**
 * RecyclerView
 *   ViewHolder: ViewHolder 是 View 的封装容器
 * RecyclerView.Adapter  数据
 * 布局管理器 LayoutManager
 * @property binding ActivityRecycleViewBinding
 */
class RecycleViewDiffUtilActivity : AppCompatActivity(){
    private lateinit var binding: ActivityRecycleViewDiffUtilBinding

    val rvDataList = arrayListOf<MyModel>()
    lateinit var customAdapter: CustomAdapterDiffUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecycleViewDiffUtilBinding.inflate(LayoutInflater.from(applicationContext))
        setContentView(binding.root)
        // recyclerview 已经在xml中设置了layoutmanager布局管理器


        for (i in 1..20) {
            rvDataList.add(MyModel("i=$i", if (i % 2 == 0) R.color.purple_200 else R.color.teal_700))
        }
        customAdapter = CustomAdapterDiffUtil(applicationContext, rvDataList)
        binding.recyclerView.adapter = customAdapter

        binding.btnChange.setOnClickListener {
            changeDatas()
        }
    }

    private fun changeDatas() {
        val newList = mutableListOf<MyModel>()
        for (i in 1..10) {
            newList.add(MyModel("i=$i", if (i % 2 == 0) R.color.purple_200 else R.color.teal_700))
        }

        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(ItemsDiffUtil(rvDataList, newList), true)
        // clear 在diffresult结果之后
        rvDataList.clear()
        rvDataList.addAll(newList)
        // 刷新列表数据方式1
//        customAdapter?.notifyDataSetChanged()
        // 刷新列表数据方式2
        diffResult.dispatchUpdatesTo(customAdapter)
    }

    class ItemsDiffUtil(
        private val oldData: List<MyModel>,
        private val newData: List<MyModel>
    ) : DiffUtil.Callback() {


        override fun getOldListSize(): Int {
            Log.i(CustomAdapterDiffUtil.tag, "getOldListSize = ${oldData.size}")
            return oldData.size
        }

        override fun getNewListSize(): Int {
            Log.i(CustomAdapterDiffUtil.tag, "getNewListSize = ${newData.size}")
            return newData.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            // 对象 == 比较内容， === 才是比较地址
            val flag = oldData[oldItemPosition] == newData[newItemPosition]
            Log.i(CustomAdapterDiffUtil.tag,
                "areItemsTheSame oldItemPosition=${oldItemPosition}" +
                        ", newItemPosition=${newItemPosition}, " +
                        "flag = ${flag}, ${oldData[oldItemPosition]}, ${newData[newItemPosition]}")
            return flag
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldModel = oldData[oldItemPosition]
            val newModel =  newData[newItemPosition]
            val flag = (oldModel.title == newModel.title && oldModel.colorResId == newModel.colorResId)
            Log.i(CustomAdapterDiffUtil.tag,
                "areContentsTheSame oldItemPosition=${oldItemPosition}" +
                        ", newItemPosition=${newItemPosition}, " +
                        "flag = ${flag}, ${oldData[oldItemPosition]}, ${newData[newItemPosition]}")
            return flag
        }
    }
}