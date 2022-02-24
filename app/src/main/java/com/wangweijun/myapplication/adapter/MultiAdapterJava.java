package com.wangweijun.myapplication.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wangweijun.myapplication.R;

import java.util.ArrayList;

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/02/24 5:08 下午
 * version: 1.0
 * desc   : recycleView 在第三页的时候复用ViewHolder,也就是复用view
 */
public class MultiAdapterJava extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "duanxia";
    private static final int View_type_0 = 0;
    private static final int View_type_1 = 1;

    private Context context;
    private ArrayList<String> dataSet;

    public MultiAdapterJava(Context context, ArrayList<String> dataSet) {
        this.context = context;
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case View_type_0: {
                View rootView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_0, parent, false);
                ViewHolder0 viewHolder0 = new ViewHolder0(rootView);
                Log.i(TAG, "onCreateViewHolder viewType="+viewType +", viewHolder0="+viewHolder0);
                return viewHolder0;
            }
            case View_type_1: {
                View rootView1 = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_1, parent, false);
                ViewHolder1 viewHolder1 = new ViewHolder1(rootView1);
                Log.i(TAG, "onCreateViewHolder viewType="+viewType +", viewHolder1="+viewHolder1);
                return viewHolder1;
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = holder.getItemViewType();
        switch (itemViewType) {
            case View_type_0:
                ViewHolder0 viewHolder0 = (ViewHolder0) holder;
                Log.i(TAG, "onBindViewHolder position="+position +", viewType="+itemViewType +", viewHolder0="+viewHolder0);
                setViewHolder0Value(viewHolder0, dataSet.get(position));
                break;
            case View_type_1:
                ViewHolder1 viewHolder1 = (ViewHolder1) holder;
                Log.i(TAG, "onBindViewHolder position="+ position +", viewType="+itemViewType +", viewHolder1="+viewHolder1);
                setViewHolder1Value(viewHolder1, dataSet.get(position));
                break;
        }
    }

    private void setViewHolder1Value(ViewHolder1 viewHolder1, String value) {
        viewHolder1.title.setText(value);
    }

    private void setViewHolder0Value(ViewHolder0 viewHolder0, String value) {
        viewHolder0.title.setText(value);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    @Override
    public int getItemViewType(int position) { // 偶数是0，基数2
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
        int viewType = position % 2 == 0 ? View_type_0: View_type_1;
        Log.i(TAG,"getItemViewType position="+position+", viewType="+viewType);
        return viewType;
    }

    public class ViewHolder0 extends RecyclerView.ViewHolder {
        TextView title;
        public ViewHolder0(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title);
        }
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {
        TextView title;
        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title);
        }
    }

}
