package com.example.firebase_test;

import android.media.Image;
import android.widget.ImageView;

public class Book_item {
    int book_image;
    String book_name;
    String book_author;
    String book_publisher;
    String book_price;
    public Book_item(int b, String n, String a, String p, String price){
        this.book_image = b;
        this.book_name = n;
        this.book_author = a;
        this.book_publisher = p;
        this.book_price = price;
    }
    public int getBook_image(){return book_image;}
    public void setBook_image(int book_image){this.book_image = book_image;}

    public String getBook_name(){return book_name;}
    public void setBook_name(String book_name){this.book_name = book_name;}

    public String getBook_author(){return book_author;}
    public void setBook_author(String book_name){this.book_author = book_author;}

    public String getBook_publisher(){return book_publisher;}
    public void setBook_publisher(String book_name){this.book_price = book_price;}

    public String getBook_price(){return book_price;}
    public void setBook_price(String book_name){this.book_price = book_price;}


}
