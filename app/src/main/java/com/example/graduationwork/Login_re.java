package com.example.graduationwork;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.graduationwork.databinding.HomepageReBinding;
import com.example.graduationwork.databinding.LoginReBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_re extends AppCompatActivity {

    private LoginReBinding binding;
    private LoginService apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LoginReBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        apiInterface = ApiClient.getClient().create(LoginService.class);

        binding.loginReBtLoginLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.loginReInfoEmail.getText().toString().trim();
                String password = binding.loginReInfoPwd.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    binding.loginReInfoEmail.setError("Email is required");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    binding.loginReInfoPwd.setError("Password is required");
                    return;
                }

                loginUser(email, password);
            }
        });

        binding.loginReBtSignupLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_re.this, Signup_re.class);
                startActivity(intent);
            }
        });
    }

    private void loginUser(String email, String password) {
        Call<Login_User> call = apiInterface.login(email, password);

        call.enqueue(new Callback<Login_User>() {
            @Override
            public void onResponse(Call<Login_User> call, Response<Login_User> response) {
                Login_User user = response.body();

                if (user != null) {
                    String name = user.getName();
                    String number = user.getNumber();
                    Toast.makeText(Login_re.this, "이름 : " + name + ", 번호 : " + number, Toast.LENGTH_SHORT).show();
                    binding.loginReBtLogin.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Login_re.this, Homepage_re.class);
                            startActivity(intent);
                        }
                    });
                } else {
                    Toast.makeText(Login_re.this, "실패", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login_User> call, Throwable t) {
                Log.e("Fail", "Error: " + t.getMessage());
            }
        });
    }
}