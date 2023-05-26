package com.example.graduationwork;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.graduationwork.databinding.HomepageReBinding;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Homepage_re extends AppCompatActivity {
    private HomepageReBinding binding;
    private List<Uploading_User> userList;
    UploadingService apiInterface = ApiClient.getClient().create(UploadingService.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = HomepageReBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadingPage();

        ImageView imageView = findViewById(R.id.home);
        imageView.setImageResource(R.drawable.homepage_home2);
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f);
        animator.setDuration(700);
        animator.start();

        binding.check.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage_re.this, Uploading_re.class);
                startActivity(intent);
            }
        });

        binding.my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage_re.this, Profile_re.class);
                startActivity(intent);
            }
        });


    }

    private void loadingPage() {
        Call<List<Uploading_User>> call = apiInterface.getRequestInfo();
        call.enqueue(new Callback<List<Uploading_User>>() {
            @Override
            public void onResponse(Call<List<Uploading_User>> call, Response<List<Uploading_User>> response) {
                if (response.isSuccessful()) {
                    userList = response.body();
                    setUpdatePage();
                }
            }

            @Override
            public void onFailure(Call<List<Uploading_User>> call, Throwable t) {

            }
        });
    }
    //homepage_content_con_1, homepage_content_supcon_1
    private void setUpdatePage() {
        List<Uploading_User> copyList = new ArrayList<>(userList); // Comparator은 순서를 바꿔서 저장시키므로 userList 복사해서 새로 만듦
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { // 안드로이드 버전 7.0 이상인지 확인
            copyList.sort(new Comparator<Uploading_User>() { // 내림차순 정렬
                @Override
                public int compare(Uploading_User u1, Uploading_User u2) {
                    return Integer.compare(u2.getPostlike(), u1.getPostlike());
                }
            });
        }
        List<Uploading_User> top4List = copyList.subList(0, Math.min(4, copyList.size()));
        ImageView[] imageViews = {
                binding.homepageContentCon1,
                binding.homepageContentCon2,
                binding.homepageContentCon3,
                binding.homepageContentCon4
        };
        for (int i=0; i<top4List.size(); i++) {
            Glide.with(this)
                    .load(top4List.get(i).getImage())
                    .into(imageViews[i]);
            int finalI = i;
            imageViews[i].setOnClickListener(view ->  {
                Intent intent = new Intent(Homepage_re.this, StylePage.class);
                intent.putExtra("selectedUser", top4List.get(finalI));
                startActivity(intent);
            });
        }
    }

}

