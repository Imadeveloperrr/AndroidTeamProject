package com.example.graduationwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


import com.example.graduationwork.databinding.HomepageReBinding;


public class Homepage_re extends AppCompatActivity {
    private HomepageReBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = HomepageReBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




    }
}
