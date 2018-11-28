package com.example.a18199.a16211160204niewei;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/11/20.
 */

public class News implements Serializable {
    private String title;
    private String date;
    private String address;

    public News(String title, String date, String address) {
        this.title = title;
        this.date = date;
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
