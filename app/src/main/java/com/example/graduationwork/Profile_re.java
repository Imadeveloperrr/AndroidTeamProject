package com.example.graduationwork;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.graduationwork.databinding.ProfileReBinding;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile_re extends AppCompatActivity {

    private ProfileReBinding binding;
    private boolean isGray = false;
    private List<Uploading_User> userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ProfileReBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        UploadingService apiInterface = ApiClient.getClient().create(UploadingService.class);
        Call<List<Uploading_User>> call = apiInterface.getUploading("ddooochii@gmail.com");
        call.enqueue(new Callback<List<Uploading_User>>() {
            @Override
            public void onResponse(Call<List<Uploading_User>> call, Response<List<Uploading_User>> response) {
                if (response.isSuccessful()) {
                    userList = response.body();
                    for (Uploading_User user : userList) {
                        setImageFromBase64(user.getImage(), binding.exUserImg);
                        Log.d("success", "response content : " + response.body());
                        Toast.makeText(Profile_re.this, "image : " + user.getId(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Profile_re.this, "실패", Toast.LENGTH_SHORT).show();
                    Log.d("success", "response content : " + response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Uploading_User>> call, Throwable t) {
                Log.e("Fail", "Error : " + t.getMessage());
                Toast.makeText(Profile_re.this, "서버오류", Toast.LENGTH_SHORT).show();
            }
        });

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
                intent.putExtra("userList", (Serializable) userList);
                startActivity(intent);
            }
        });
    }
    private void setImageFromBase64(String base64Image, ImageView imageView) {
        // Base64 형식의 이미지를 디코딩
        byte[] decodedBytes = Base64.decode(base64Image, Base64.DEFAULT);
        // 디코딩된 바이트를 비트맵으로 변환
        Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
        // ImageView에 비트맵 설정
        imageView.setImageBitmap(decodedBitmap);
    }
}
