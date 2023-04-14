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
    private SignupService signupService;
    String email = binding.signupReEx.getText().toString().trim();
    String pw = binding.signupRePwdEx.getText().toString().trim();
    String name = binding.signupReNameEx.getText().toString().trim();
    String number = binding.signupRePhoneEx.getText().toString().trim();
    String tall = binding.signupReHeightEx.getText().toString().trim();
    String weight = binding.signupReWeightEx.getText().toString().trim();
    String gender = binding.signupReGenderEx.getText().toString().trim();
    String foot = binding.signupReShoesEx.getText().toString().trim();

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
                .baseUrl("http://ec2-3-34-179-47.ap-northeast-2.compute.amazonaws.com")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        signupService = retrofit.create(SignupService.class);

        binding.signupReBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(email)) {
                    EmptyCheckDialog(0);
                    return;
                }
                if(TextUtils.isEmpty(pw)) {
                    EmptyCheckDialog(1);
                    return;
                }
                if(TextUtils.isEmpty(name)) {
                    EmptyCheckDialog(2);
                    return;
                }
                if(TextUtils.isEmpty(number)) {
                    EmptyCheckDialog(3);
                    return;
                }
                if(TextUtils.isEmpty(tall)) {
                    EmptyCheckDialog(4);
                    return;
                }
                if(TextUtils.isEmpty(weight)) {
                    EmptyCheckDialog(5);
                    return;
                }
                if(TextUtils.isEmpty(gender)) {
                    EmptyCheckDialog(6);
                    return;
                }
                if(TextUtils.isEmpty(foot)) {
                    EmptyCheckDialog(7);
                    return;
                }
                SignupStart();
            }
        });
    }

    private void EmptyCheckDialog(int i) {
        if(i == 0){
            Toast.makeText(Signup_re.this, "오류 : 이메일을 다시 입력하세요.", Toast.LENGTH_SHORT).show();
            binding.signupReEx.setText("");
        } else if (i == 1) {
            Toast.makeText(Signup_re.this, "오류 : 패스워드를 다시 입력하세요.", Toast.LENGTH_SHORT).show();
            binding.signupRePwdEx.setText("");
        }
        else if (i == 2) {
            Toast.makeText(Signup_re.this, "오류 : 이름을 다시 입력하세요.", Toast.LENGTH_SHORT).show();
            binding.signupReNameEx.setText("");
        }
        else if (i == 3) {
            Toast.makeText(Signup_re.this, "오류 : 번호를 다시 입력하세요.", Toast.LENGTH_SHORT).show();
            binding.signupRePhoneEx.setText("");
        }
        else if (i == 4) {
            Toast.makeText(Signup_re.this, "오류 : 키를 다시 입력하세요.", Toast.LENGTH_SHORT).show();
            binding.signupReHeightEx.setText("");
        }
        else if (i == 5) {
            Toast.makeText(Signup_re.this, "오류 : 몸무게를 다시 입력하세요.", Toast.LENGTH_SHORT).show();
            binding.signupReWeightEx.setText("");
        }
        else if (i == 6) {
            Toast.makeText(Signup_re.this, "오류 : 성별을 다시 입력하세요.", Toast.LENGTH_SHORT).show();
            binding.signupReGenderEx.setText("");
        }
        else if (i == 7) {
            Toast.makeText(Signup_re.this, "오류 : 신발 사이즈를 다시 입력하세요.", Toast.LENGTH_SHORT).show();
            binding.signupReShoesEx.setText("");
        }
    }
    private void SignupStart(){

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