package com.example.graduationwork;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginAPI {
    @FormUrlEncoded
    @POST("Logtest.php")
    Call<Login_User> login(@Field("email") String email, @Field("pw") String pw);
}