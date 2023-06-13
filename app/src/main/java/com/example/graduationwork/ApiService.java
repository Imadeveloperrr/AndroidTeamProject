package com.example.graduationwork;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @FormUrlEncoded
    @POST("login.php")
    Call<Login_User> login(
            @Field("email") String email,
            @Field("pw") String pw
    );

    @FormUrlEncoded
    @POST("register.php")
    Call<JsonObject> registerUser(
            @Field("email") String email,
            @Field("pw") String pw,
            @Field("name") String name,
            @Field("number") String number,
            @Field("tall") String tall,
            @Field("weight") String weight,
            @Field("gender") String gender,
            @Field("foot") String foot
    );

    @GET("stylepage.php")
    Call<List<JsonObject>> likeUser(
            @Query("id") int id,
            @Query("like_email") String like_email,
            @Query("postlike") int postlike,
            @Query("type") int type
    );

    @GET("image.php")
    Call<List<Uploading_User>> getUploading(
            @Query("user_email") String user_email
    );

    @GET("image.php")
    Call<List<Uploading_User>> getRequestInfo(

    );

    @FormUrlEncoded
    @POST("image.php")
    Call<Uploading_User> insertUploading(
            @Field("user_email") String user_email,
            @Field("title") String title,
            @Field("image") String image
    );

    @FormUrlEncoded
    @POST("image.php")
    Call<Uploading_User> updateUploading(
            @Field("id") int id,
            @Field("user_email") String user_email,
            @Field("title") String title,
            @Field("image") String image,
            @Field("postlike") int postlike
    );

}
