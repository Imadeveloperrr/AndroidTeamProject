package com.example.graduationwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.example.graduationwork.databinding.LikelistBinding;

public class Likelist extends AppCompatActivity {

    private LikelistBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LikelistBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.LikelistBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Likelist.this, StylePage.class);
                startActivity(intent);
            }
        });

        binding.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Likelist.this, Homepage_re.class);
                startActivity(intent);
            }
        });

        binding.serch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Likelist.this, Search.class);
                startActivity(intent);
            }
        });

        binding.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Likelist.this, Uploading_re.class);
                startActivity(intent);
            }
        });

        binding.my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Likelist.this, Profile_re.class);
                startActivity(intent);
            }
        });


    }
}
