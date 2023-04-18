package com.example.graduationwork;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.graduationwork.databinding.ProfileReBinding;

public class Profile_re extends AppCompatActivity {

    private ProfileReBinding binding;
    private boolean isGray = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ProfileReBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.profileReCaStyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isGray) {
                    binding.profileReCaStyle.setTextColor(Color.BLACK);
                    isGray = false;
                } else {
                    binding.profileReCaStyle.setTextColor(Color.GRAY);
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

        // ImageView 클릭 이벤트 설정
        binding.exUserImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 새로운 액티비티 생성 및 이미지 전달
                Intent intent = new Intent(Profile_re.this, StylePage.class);
                startActivity(intent);
            }
        });
    }
}
