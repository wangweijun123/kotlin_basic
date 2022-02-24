package com.yqritc.recyclerviewmultipleviewtypesadapter.sample.binder;

import com.wangweijun.myapplication.R;
import com.yqritc.recyclerviewmultipleviewtypesadapter.DataBindAdapter;
import com.yqritc.recyclerviewmultipleviewtypesadapter.DataBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by yqritc on 2015/03/20.
 */
public class Sample3Binder extends DataBinder<Sample3Binder.ViewHolder> {

    public Sample3Binder(DataBindAdapter dataBindAdapter) {
        super(dataBindAdapter);
    }

    @Override
    public ViewHolder newViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.layout_sample3, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void bindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTitleText;
        ImageView mImageView;
        TextView mContent;

        public ViewHolder(View view) {
            super(view);
            mTitleText = (TextView) view.findViewById(R.id.title_type3);
            mImageView = (ImageView) view.findViewById(R.id.image_type3);
            mContent = (TextView) view.findViewById(R.id.content_type3);
        }
    }
}
