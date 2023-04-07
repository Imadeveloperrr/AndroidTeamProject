package com.example.graduationwork;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.graduationwork.databinding.LoginBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Login extends AppCompatActivity {

    private LoginBinding binding;
    private LoginAPI apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        apiInterface = ApiClient.getClient().create(LoginAPI.class);

        binding.loginButtonPrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.email.getText().toString().trim();
                String password = binding.password.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    binding.email.setError("Email is required");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    binding.password.setError("Password is required");
                    return;
                }

                loginUser(email, password);
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
                    Toast.makeText(Login.this, "이름 : " + name + ", 번호 : " + number, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Login.this, "실패", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login_User> call, Throwable t) {
                Toast.makeText(Login.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}