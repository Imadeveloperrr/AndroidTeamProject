package com.example.graduationwork;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;

import com.example.graduationwork.databinding.HomepageReBinding;
import com.example.graduationwork.databinding.UploadingReCommentsBinding;

public class Uploading_re_comments extends AppCompatActivity {
    private UploadingReCommentsBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = UploadingReCommentsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.uploadingReCommentBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Uploading_re_comments.this, Uploading_re.class);
                startActivity(intent);
            }
        });


    }
}
