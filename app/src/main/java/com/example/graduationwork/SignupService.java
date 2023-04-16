package com.example.graduationwork;


import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SignupService {
    @FormUrlEncoded
    @POST("Resitest.php")
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
}