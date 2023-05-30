package com.example.graduationwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.graduationwork.databinding.CommentspageBinding;


public class CommentsPage extends AppCompatActivity {

    private CommentspageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = CommentspageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.commentsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CommentsPage.this, StylePage.class);
                startActivity(intent);
            }
        });

        binding.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CommentsPage.this, Homepage_re.class);
                startActivity(intent);
            }
        });

        binding.serch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CommentsPage.this, Search.class);
                startActivity(intent);
            }
        });

        binding.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CommentsPage.this, Uploading_re.class);
                startActivity(intent);
            }
        });

        binding.my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CommentsPage.this, Profile_re.class);
                startActivity(intent);
            }
        });
    }
}
