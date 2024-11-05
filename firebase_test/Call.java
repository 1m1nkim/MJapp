package com.example.firebase_test;

public class Call {
    String deName;
    String deNum;

    public Call(){}


    public String getDeName() {
        return deName;
    }

    public void setDeName(String deName) {
        this.deName = deName;
    }

    public String getDeNum() {
        return deNum;
    }

    public void setDeNum(String deNum) {
        this.deNum = deNum;
    }
    public Call(String deName, String deNum){
        this.deName = deName;
        this.deNum = deNum;
    }
}
//수정ㅇ
