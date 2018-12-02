package com.example.a18199.a16211160204niewei;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class NormalAdapter extends RecyclerView.Adapter<NormalAdapter.VH>{
    public static class VH extends RecyclerView.ViewHolder{
        public final TextView title;
        public final TextView date;
        public VH(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.textView1);
            date =(TextView) v.findViewById(R.id.textView2);
        }
    }
    private List<News> mDatas;
    public NormalAdapter(List<News> data) {
        this.mDatas = data;
    }
    //③ 在Adapter中实现3个方法
    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.title.setText(mDatas.get(position).getTitle());
        holder.date.setText(mDatas.get(position).getDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //item 点击事件
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        //LayoutInflater.from指定写法
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
        return new VH(v);
    }
}
