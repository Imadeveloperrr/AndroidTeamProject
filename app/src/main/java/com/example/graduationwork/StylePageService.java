package com.example.graduationwork;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;

public interface StylePageService {

    @GET
    Call<List<JsonObject>> likeUser(
            @Field("id") int id,
            @Field("like_email") String like_email,
            @Field("postlike") int postlike
    );
}
