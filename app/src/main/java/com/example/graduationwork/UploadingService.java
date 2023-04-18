package com.example.graduationwork;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UploadingService {
    @GET("image.php")
    Call<List<Uploading_User>> getUploading(
            @Query("id") String id
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
