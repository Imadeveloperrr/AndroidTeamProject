package com.example.graduationwork;


import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SignupService {
    @FormUrlEncoded
    @POST("Resitest.php")
    Call<JSONObject> registerUser(
            @Field("email") String email,
            @Field("pw") String pw,
            @Field("name") String name,
            @Field("number") String number
    );
}