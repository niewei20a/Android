package com.example.a18199.a16211160204niewei;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/11/20.
 */

public class NewsThread extends Thread {
    private Handler handler;
    private String url;
    public static final String TYPE ="CONTENT_OF_NEWS";

    public NewsThread(Handler handler, String url) {
        this.handler = handler;
        this.url = url;
    }

    @Override
    public void run() {
        ArrayList<News> newsArrayList = new ArrayList<>();
        try {
                Document document = Jsoup.connect(url).timeout(10000).get();
                Elements elements = document.getElementsByAttributeValue("class", "newslist");
                Elements el=elements.select("li");
                for (int i = 0; i < el.size() ; i++) {
                    Elements els=el.select("A");
                    String title=els.get(i).attr("title");
                    String address=els.get(i).attr("href");
                    String address1="http://jwc.wzu.edu.cn/"+address.substring(2);
                    String time=els.get(i).getElementsByAttributeValue("class","newslistatime").text();
                    News news=new News(title,time,address1);
                    newsArrayList.add(news);
                    Log.d("TAG", "run: "+news.toString());
                }
            }catch (Exception e){
            }
        Message message=handler.obtainMessage();//获取message
        Bundle bundle=new Bundle();
        bundle.putSerializable(TYPE,newsArrayList);
        message.setData(bundle);
        handler.sendMessage(message);//发送message
    }
}
