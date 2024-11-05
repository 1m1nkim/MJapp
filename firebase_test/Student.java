package com.example.firebase_test;

public class Student {
    String stuName;
    String stuNum;
    String stuBirth;
    String stuPasswd;

    public Student(){}

    public String getstuName() {
        return stuName;
    }

    public void setname(String stuName) {
        this.stuName = stuName;
    }

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public String getStuBirth() { return stuBirth; }

    public void setStuBirth(String stuBirth) { this.stuBirth = stuBirth;  }

    public String getStuPasswd() { return stuPasswd; }

    public void setStuPasswd(String stuPasswd) {this.stuPasswd = stuPasswd; }
    public Student(String stuBirth, String stuName, String stuNum, String stuPasswd){
        this.stuBirth = stuBirth;
        this.stuName = stuName;
        this.stuNum = stuNum;
        this.stuPasswd = stuPasswd;
    }
}