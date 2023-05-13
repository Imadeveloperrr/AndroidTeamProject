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
    }
}
