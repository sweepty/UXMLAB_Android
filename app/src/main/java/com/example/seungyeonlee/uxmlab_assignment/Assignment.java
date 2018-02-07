package com.example.seungyeonlee.uxmlab_assignment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by seungyeonlee on 2018. 2. 1..
 */

public class Assignment {

    private String asName;
    private String asContent;
    private String asDue;

    public String getAsName() {
        return asName;
    }

    public void setAsName(String asName) {
        this.asName = asName;
    }

    public String getAsContent() {
        return asContent;
    }

    public void setAsContent(String asContent) {
        this.asContent = asContent;
    }

    public String getAsDue() {
        return asDue;
    }

    public void setAsDue(String asDue) {
        this.asDue = asDue;
    }


}
