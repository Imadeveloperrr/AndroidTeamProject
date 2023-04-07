package com.example.graduationwork;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.graduationwork.databinding.SignupBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class Signup extends AppCompatActivity {

    private SignupBinding binding;
    private SignupService signupService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = SignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-15-164-221-114.ap-northeast-2.compute.amazonaws.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        signupService = retrofit.create(SignupService.class);

        binding.signupButtonPrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.exMail.getText().toString();
                String pw = binding.exPwd.getText().toString();
                String name = binding.exName.getText().toString();
                String number = binding.exPhoneNumber.getText().toString();
                try {
                    email = URLEncoder.encode(email, "UTF-8");
                    pw = URLEncoder.encode(pw, "UTF-8");
                    name = URLEncoder.encode(name, "UTF-8");
                    number = URLEncoder.encode(number, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Call<JSONObject> call = signupService.registerUser(email, pw, name, number);
                call.enqueue(new Callback<JSONObject>() {
                    @Override
                    public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            boolean success = false;
                            try {
                                success = response.body().getBoolean("success");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if (success) {
                                Toast.makeText(getApplicationContext(), "성공", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Signup.this, Login.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "실패", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Log.e("onResponse", "서버 응답 실패");
                        }
                    }

                    @Override
                    public void onFailure(Call<JSONObject> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(getApplicationContext(), "예외 1", Toast.LENGTH_SHORT).show();

                        Log.e("Signup", "Failed request: " + call.request());
                        Log.e("Signup", "Error message: " + t.getMessage());
                    }
                });
            }
        });
    }
}
