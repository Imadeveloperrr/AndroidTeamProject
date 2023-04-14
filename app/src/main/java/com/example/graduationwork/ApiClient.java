package com.example.graduationwork;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();

    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://ec2-3-34-179-47.ap-northeast-2.compute.amazonaws.com")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    public static Retrofit getClient() {
        return retrofit;
    }
}
