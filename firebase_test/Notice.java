package com.example.firebase_test;

public class Notice {
    String info_title;
    String info_writer;
    String info_date;
    String info_main;
    String info_imgurl;
    int nindex;

    public Notice() { }

    public Notice(String title, String writer, String date, String main, String imgurl){
        info_title = title;
        info_writer = writer;
        info_date = date;
        info_main = main;
        info_imgurl = imgurl;

    }

    public String getInfo_title() {
        return info_title;
    }

    public void setInfo_title(String info_title){
        this.info_title = info_title;
    }

    public String getInfo_writer() {
        return info_writer;
    }

    public void setInfo_writer(String info_writer){
        this.info_writer = info_writer;
    }

    public String getInfo_date() {
        return info_date;
    }

    public void setInfo_date(String info_date){
        this.info_date = info_date;
    }

    public String getInfo_main() {
        return info_main;
    }

    public void setInfo_main(String info_main){
        this.info_main = info_main;
    }

    public String getInfo_imgurl() {
        return info_imgurl;
    }

    public void setInfo_imgurl(String info_imgurl){
        this.info_imgurl = info_imgurl;
    }

    public int getnIndex(){
        return nindex;
    }

    public void setnIndex(int nindex){
        this.nindex = nindex;
    }


}

