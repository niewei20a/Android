package com.example.a18199.a16211160204niewei;

import android.content.Context;
import android.support.annotation.NonNull;
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
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_layout,null,false);
        }
        TextView tv1= (TextView) convertView.findViewById(R.id.textView1);
        TextView tv2= (TextView) convertView.findViewById(R.id.textView2);
        News news = newsList.get(position);

        tv1.setText(String.format("[%03d] ",position)+news.getTitle());
        tv2.setText(" "+news.getDate());
        return convertView;
    }
}
