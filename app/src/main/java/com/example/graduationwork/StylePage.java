package com.example.graduationwork;


import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.example.graduationwork.databinding.CommentspageBinding;
import com.example.graduationwork.databinding.ProfileReBinding;
import com.example.graduationwork.databinding.StylepageBinding;


public class StylePage extends AppCompatActivity {
    private StylepageBinding binding;
    private ProfileReBinding secondScreenBinding;
    private CommentspageBinding thirdScreenBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = StylepageBinding.inflate(getLayoutInflater());
        secondScreenBinding = ProfileReBinding.inflate(getLayoutInflater());
        thirdScreenBinding = CommentspageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.styleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(secondScreenBinding.getRoot());
            }
        });

        binding.StylePageCommentsEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StylePage.this, CommentsPage.class);
                startActivity(intent);
            }
        });

        secondScreenBinding.profileReCaStyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StylePage.this, StylePage.class);
                startActivity(intent);
            }
        });

    }
}


