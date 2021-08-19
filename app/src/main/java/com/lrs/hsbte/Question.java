package com.lrs.hsbte;

import com.google.firebase.database.IgnoreExtraProperties;

public class Question {
    private String que;

    public Question() {
        //this constructor is required
    }

    public Question(String que) {
        this.que = que;
    }

    public String getQue() {
        return que;
    }

}