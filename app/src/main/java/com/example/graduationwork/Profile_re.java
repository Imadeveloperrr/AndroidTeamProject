package com.example.graduationwork;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.graduationwork.databinding.ProfileReBinding;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile_re extends AppCompatActivity {

    private ProfileReBinding binding;
    private boolean isGray = false;
    private List<Uploading_User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ProfileReBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        UploadingService apiInterface = ApiClient.getClient().create(UploadingService.class);
        Call<List<Uploading_User>> call = apiInterface.getUploading("ddooochii@gmail.com");
        call.enqueue(new Callback<List<Uploading_User>>() {
            @Override
            public void onResponse(Call<List<Uploading_User>> call, Response<List<Uploading_User>> response) {
                if (response.isSuccessful()) {
                    userList = response.body();
                    ProfileFragment profileFragment = new ProfileFragment();
                    profileFragment.setUserList(userList); // Set the userList in the fragment.

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.profile_re_show_img, profileFragment);
                    transaction.commit();
                    binding.profileRePostCount.setText(String.valueOf(userList.size()));

                } else {
                    Toast.makeText(Profile_re.this, "실패", Toast.LENGTH_SHORT).show();
                    Log.d("success", "response content : " + response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Uploading_User>> call, Throwable t) {
                Log.e("Fail", "Error : " + t.getMessage());
                Toast.makeText(Profile_re.this, "서버오류", Toast.LENGTH_SHORT).show();
            }
        });

        binding.profileReCaStyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isGray) {
                    binding.profileReCaStyle.setTextColor(Color.GRAY);
                    isGray = false;
                } else {
                    binding.profileReCaStyle.setTextColor(Color.BLACK);
                    isGray = true;
                }
            }
        });

        binding.profileReCaReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isGray) {
                    binding.profileReCaReview.setTextColor(Color.BLACK);
                    isGray = false;
                } else {
                    binding.profileReCaReview.setTextColor(Color.GRAY);
                    isGray = true;
                }
            }
        });

        binding.profileReCaCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isGray) {
                    binding.profileReCaCollection.setTextColor(Color.BLACK);
                    isGray = false;
                } else {
                    binding.profileReCaCollection.setTextColor(Color.GRAY);
                    isGray = true;
                }
            }
        });

        binding.profileReCaScrap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isGray) {
                    binding.profileReCaScrap.setTextColor(Color.BLACK);
                    isGray = false;
                } else {
                    binding.profileReCaScrap.setTextColor(Color.GRAY);
                    isGray = true;
                }
            }
        });

        binding.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile_re.this, Homepage_re.class);
                startActivity(intent);
            }
        });

        binding.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile_re.this, Uploading_re.class);
                startActivity(intent);
            }
        });

    }

}
