package com.example.seungyeonlee.uxmlab_assignment;

import android.annotation.SuppressLint;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by seungyeonlee on 2018. 2. 18..
 */

public interface Api {

    String BASE_URL = "http://10.0.2.2/~seungyeonlee/";
    @Multipart
    @POST("Api.php?apicall=upload")
    Call<MyResponse> uploadImage(
            @Part("image\"; filename=\"myfile.jpg\" ") RequestBody file,
            @Part("desc") RequestBody desc
    );


    @FormUrlEncoded
    @POST("add_assignment.php")
    Call <List<Result>> addAssignment(
            @Field("hw_name") String hw_name, @Field("hw_content") String hw_content, @Field("hw_due") String hw_due
    );

}
