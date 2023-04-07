package com.example.graduationwork;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.example.graduationwork.databinding.ProfileReBinding;



public class Profile_re extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ProfileReBinding binding = ProfileReBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.profileReCaStyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.profileReCaStyle.setVisibility(View.VISIBLE);
                setContentView(R.layout.stylepage);
            }
        });
    }
}
