package com.example.graduationwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.example.graduationwork.databinding.ProfileReBinding;



public class Profile_re extends AppCompatActivity {

    private ProfileReBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ProfileReBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.profileReCaStyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile_re.this, StylePage.class);
                startActivity(intent);
            }
        });
    }
}
