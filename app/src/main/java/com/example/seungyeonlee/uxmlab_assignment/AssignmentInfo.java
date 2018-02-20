package com.example.seungyeonlee.uxmlab_assignment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by seungyeonlee on 2018. 2. 20..
 */

public class AssignmentInfo {

    @SerializedName("asName")
    @Expose
    private String asName;

    @SerializedName("asContent")
    @Expose
    private String asContent;

    @SerializedName("asDue")
    @Expose
    private String asDue;

    @SerializedName("error")
    @Expose
    boolean error;


}
