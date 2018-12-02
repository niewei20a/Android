package com.example.a18199.a16211160204niewei;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/11/20.
 */

public class NewsAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<News> newsList;

    public NewsAdapter(Context context, ArrayList<News> news) {
        super(context,android.R.layout.simple_list_item_1,news);
        this.context = context;
        this.newsList = news;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("getView " , position + " " + convertView);
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_layout,null,false);
            viewHolder = new ViewHolder();
            viewHolder.textView1 = (TextView) convertView.findViewById(R.id.textView1);
            viewHolder.textView2 = (TextView) convertView.findViewById(R.id.textView2);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView1.setText(String.format("[%03d] ",position)+newsList.get(position).getTitle());
        viewHolder.textView2.setText(" "+newsList.get(position).getDate());
        return  convertView;
    }
    public static class ViewHolder {
        public TextView textView1;
        public TextView textView2;
    }
}
