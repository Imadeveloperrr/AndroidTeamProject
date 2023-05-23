package com.example.graduationwork;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StylePageService {

    @GET("stylepage.php")
    Call<List<JsonObject>> likeUser(
            @Query("id") int id,
            @Query("like_email") String like_email,
            @Query("postlike") int postlike
    );
}
