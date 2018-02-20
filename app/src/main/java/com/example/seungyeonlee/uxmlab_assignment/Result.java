package com.example.seungyeonlee.uxmlab_assignment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by seungyeonlee on 2018. 2. 20..
 */

public class Result {
    @SerializedName("result")
    @Expose
    private int result;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

}
