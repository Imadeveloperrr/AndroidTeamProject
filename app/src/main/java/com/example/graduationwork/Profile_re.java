package com.example.graduationwork;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
    boolean isViewVisible = false;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ProfileReBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ImageView imageView2 = findViewById(R.id.my);
        imageView2.setImageResource(R.drawable.homepage_profile2);
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView2, "alpha", 0f, 1f);
        animator.setDuration(700);
        animator.start();

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

        binding.serch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile_re.this, Search.class);
                startActivity(intent);
            }
        });

        binding.profileReFollowing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile_re.this, Following_followerPage.class);
                startActivity(intent);
            }
        });

        binding.profileReFollowerBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isViewVisible) {
                    // 이전 뷰로 돌아가기
                    binding.profileReFollowerBox2.setVisibility(View.GONE);
                    binding.profileReFollowerBox.setVisibility(View.VISIBLE);
                    binding.profileReFollowerInfo2.setVisibility(View.GONE);
                    binding.profileReFollowerInfo.setVisibility(View.VISIBLE);
                    isViewVisible = false;
                } else {
                    // 전환할 뷰로 전환
                    binding.profileReFollowerBox.setVisibility(View.GONE);
                    binding.profileReFollowerBox2.setVisibility(View.VISIBLE);
                    binding.profileReFollowerInfo.setVisibility(View.GONE);
                    binding.profileReFollowerInfo2.setVisibility(View.VISIBLE);
                    isViewVisible = true;
                }
            }
        });

        binding.profileReFollowerBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 이전 뷰로 돌아가기
                binding.profileReFollowerBox2.setVisibility(View.GONE);
                binding.profileReFollowerBox.setVisibility(View.VISIBLE);
                binding.profileReFollowerInfo2.setVisibility(View.GONE);
                binding.profileReFollowerInfo.setVisibility(View.VISIBLE);
                isViewVisible = false;
            }
        });









    }

}
