package com.example.firebase_test;

public class Book_info {
    String sell_name_ed;
    String sell_author_ed;
    String sell_publisher_ed;
    String sell_publishd_ed;
    String sell_price_ed;
    String bozon;
    String imageUrl;
    String imageUrl2;
    String sudan;
    String plain;
    String area;
    int index;
    String name_num;
    String key;
    public Book_info(){

    }
    public Book_info(String name, String author, String publisher, String publishd, String price, String bozon,
                     String imageUrl, String imageUrl2, String sudan, String plain, String area, String name_num, String key){
        sell_name_ed = name;
        sell_author_ed = author;
        sell_publisher_ed = publisher;
        sell_publishd_ed = publishd;
        sell_price_ed = price;
        this.bozon = bozon;
        this.imageUrl = imageUrl;
        this.imageUrl2 = imageUrl2;
        this.sudan = sudan;
        this.plain = plain;
        this.area = area;
        this.name_num = name_num;
        this.key = key;
    }

    public String getSell_name_ed() {
        return sell_name_ed;
    }

    public void setSell_name_ed(String sell_name_ed) {
        this.sell_name_ed = sell_name_ed;
    }

    public String getSell_author_ed() {
        return sell_author_ed;
    }

    public void setSell_author_ed(String sell_author_ed) {
        this.sell_author_ed = sell_author_ed;
    }

    public String getSell_publisher_ed() {
        return sell_publisher_ed;
    }

    public void setSell_publisher_ed(String sell_publisher_ed) {
        this.sell_publisher_ed = sell_publisher_ed;
    }

    public String getSell_publishd_ed() {
        return sell_publishd_ed;
    }

    public void setSell_publishd_ed(String sell_publishd_ed) {
        this.sell_publishd_ed = sell_publishd_ed;
    }

    public String getSell_price_ed() {
        return sell_price_ed;
    }

    public void setSell_price_ed(String sell_price_ed) {
        this.sell_price_ed = sell_price_ed;
    }

    public String getBozon() {
        return bozon;
    }

    public void setBozon(String bozon) {
        this.bozon = bozon;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getImageUrl() {
        if(imageUrl == "" && imageUrl2 == ""){
            return "";
        }else if(imageUrl == "" && imageUrl2 != ""){
            return imageUrl2;
        }else{
            return imageUrl;
        }
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl2() {
        return imageUrl2;
    }

    public void setImageUrl2(String imageUrl2) {
        this.imageUrl2 = imageUrl2;
    }

    public String getSudan() {
        return sudan;
    }

    public void setSudan(String sudan) {
        this.sudan = sudan;
    }

    public String getPlain() {
        return plain;
    }

    public void setPlain(String plain) {
        this.plain = plain;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName_num() {
        return name_num;
    }

    public void setName_num(String name_num) {
        this.name_num = name_num;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
