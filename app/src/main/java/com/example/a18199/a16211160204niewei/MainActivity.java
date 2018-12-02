package com.example.a18199.a16211160204niewei;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lv;
    private Button bt;
    private View processView;
    private ArrayList<News> list_data;
    private int last_index;
    private int total_index;
    private int Max_Data_Num = 0;
    public String LOG_TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        lv = (ListView) findViewById(R.id.ListView);
        bt = (Button) findViewById(R.id.button);
        list_data = new ArrayList<>();
        processView = LayoutInflater.from(this).inflate(R.layout.foot_layout, null, false);
        lv.addFooterView(processView);

        final Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                list_data.addAll(list_data.size(), (ArrayList<News>) bundle.getSerializable(NewsThread.TYPE));
                NewsAdapter adapter = new NewsAdapter(MainActivity.this, list_data);
                if (lv.getCount() == 0) {
                    lv.setAdapter(adapter);
                } else {
                    processView.setVisibility(View.GONE);
                    lv.requestLayout();
                    adapter.notifyDataSetChanged();
                    processView.setVisibility(View.VISIBLE);
                    if (Max_Data_Num >= 2)
                        lv.removeFooterView(processView);
                }
                return false;
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://jwc.wzu.edu.cn/index/wjtz.htm";
                if (list_data.size() == 0) {
                    NewsThread newsThread = new NewsThread(handler, url);
                    newsThread.start();
                } else {
                    Intent intent = new Intent(MainActivity.this, RecyclerViewActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("name", list_data);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News news = list_data.get(position);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(news.getAddress()));
                startActivity(intent);
            }
        });
        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if ((last_index == total_index) && (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) && (Max_Data_Num <= 2)) {
                    String last_url = "http://jwc.wzu.edu.cn/index/wjtz/" + (53 - Max_Data_Num) + ".htm";
                    new NewsThread(handler, last_url).start();
                    Max_Data_Num++;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                last_index = firstVisibleItem + visibleItemCount;
                total_index = totalItemCount;
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.i(LOG_TAG, "onKeyDown: keyCode -- " + keyCode);
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                moveTaskToBack(false);
//                break;
                return false;//拦截事件
            case KeyEvent.KEYCODE_MENU:
                Log.i(LOG_TAG, "KeyEvent.KEYCODE_MENU");
                break;
            case KeyEvent.KEYCODE_HOME:
                Log.i(LOG_TAG, "KeyEvent.KEYCODE_HOME");
                // 收不到
                break;
            case KeyEvent.KEYCODE_APP_SWITCH:
                Log.i(LOG_TAG, "KeyEvent.KEYCODE_APP_SWITCH");
                // 收不到
                break;
            default:
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
}
