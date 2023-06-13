package com.example.graduationwork;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.graduationwork.databinding.SignupReBinding;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import java.util.Arrays;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class Signup_re extends AppCompatActivity {

    private SignupReBinding binding;
    private ApiService signupService;
    String email, pw, name, number, tall, weight, gender, foot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = SignupReBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-43-201-254-101.ap-northeast-2.compute.amazonaws.com")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        signupService = retrofit.create(ApiService.class);

        binding.signupReBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signup_re.this, Login_re.class);
                startActivity(intent);
            }
        });

        binding.signupReBt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                email = binding.signupReEx.getText().toString().trim();
                pw = binding.signupRePwdEx.getText().toString().trim();
                name = binding.signupReNameEx.getText().toString().trim();
                number = binding.signupRePhoneEx.getText().toString().trim();
                tall = binding.signupReHeightEx.getText().toString().trim();
                weight = binding.signupReWeightEx.getText().toString().trim();
                gender = binding.signupReGenderEx.getText().toString().trim();
                foot = binding.signupReShoesEx.getText().toString().trim();
                Log.e("Click", "email : " + email);
                if(TextUtils.isEmpty(email)) {
                    Toast.makeText(Signup_re.this, "오류 : 이메일을 다시 입력하세요.", Toast.LENGTH_SHORT).show();
                    binding.signupReEx.setText("");
                    return;
                }
                if(TextUtils.isEmpty(pw)) {
                    Toast.makeText(Signup_re.this, "오류 : 패스워드를 다시 입력하세요.", Toast.LENGTH_SHORT).show();
                    binding.signupRePwdEx.setText("");
                    return;
                }
                if(TextUtils.isEmpty(name)) {
                    Toast.makeText(Signup_re.this, "오류 : 이름을 다시 입력하세요.", Toast.LENGTH_SHORT).show();
                    binding.signupReNameEx.setText("");
                    return;
                }
                if(TextUtils.isEmpty(number)) {
                    Toast.makeText(Signup_re.this, "오류 : 번호를 다시 입력하세요.", Toast.LENGTH_SHORT).show();
                    binding.signupRePhoneEx.setText("");
                    return;
                }
                if(TextUtils.isEmpty(tall)) {
                    Toast.makeText(Signup_re.this, "오류 : 키를 다시 입력하세요.", Toast.LENGTH_SHORT).show();
                    binding.signupReHeightEx.setText("");
                    return;
                }
                if(TextUtils.isEmpty(weight)) {
                    Toast.makeText(Signup_re.this, "오류 : 몸무게를 다시 입력하세요.", Toast.LENGTH_SHORT).show();
                    binding.signupReWeightEx.setText("");
                    return;
                }
                if(TextUtils.isEmpty(gender)) {
                    Toast.makeText(Signup_re.this, "오류 : 성별을 다시 입력하세요.", Toast.LENGTH_SHORT).show();
                    binding.signupReGenderEx.setText("");
                    return;
                }
                if(TextUtils.isEmpty(foot)) {
                    Toast.makeText(Signup_re.this, "오류 : 신발 사이즈를 다시 입력하세요.", Toast.LENGTH_SHORT).show();
                    binding.signupReShoesEx.setText("");;
                    return;
                }
                SignupStart(email, pw, name, number, tall, weight, gender, foot);
            }
        });
    }

    private void SignupStart(String email,String  pw,String  name,String  number,String  tall,String  weight,String  gender,String  foot){

        Call<JsonObject> call = signupService.registerUser(email, pw, name, number, tall, weight, gender, foot);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful() && response.body() != null) {
                    boolean success = false;
                    try {
                        JsonElement jsonElement = JsonParser.parseString(response.body().toString());
                        JsonObject jsonObject = jsonElement.getAsJsonObject();
                        Log.e("success", "response content : " + jsonObject.toString());
                        success = jsonObject.get("success").getAsBoolean();
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                    }
                    if (success) {
                        Toast.makeText(getApplicationContext(), "성공", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Signup_re.this, Login_re.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "실패", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("onResponse", "서버 응답 실패");
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "예외 발생", Toast.LENGTH_SHORT).show();

                Log.e("Signup", "Failed request: " + call.request());
                Log.e("Signup", "Error message: " + t.getMessage());
                Log.e("Signup", "Error cause: " + t.getCause());
                Log.e("Signup", "Error stack trace: " + Arrays.toString(t.getStackTrace()));
            }
        });
    }
}