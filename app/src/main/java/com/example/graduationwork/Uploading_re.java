package com.example.graduationwork;


import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.graduationwork.databinding.UploadingReBinding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Uploading_re extends AppCompatActivity {
    View View;
    private UploadingReBinding binding;
    private UploadingService apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = UploadingReBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        apiInterface = ApiClient.getClient().create(UploadingService.class);


        binding.styleUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            String base64Image = imageToBase64(imageUri);
            Login_User user = new Login_User();
            //String user_email = user.getEmail();
            String user_email = "ddooochii@gmail.com";
            String title = "테스트";
            Call<Uploading_User> call = apiInterface.insertUploading(user_email, title, base64Image);
            call.enqueue(new Callback<Uploading_User>() {
                @Override
                public void onResponse(Call<Uploading_User> call, Response<Uploading_User> response) {
                    if (response.isSuccessful()) {
                        Log.d("Uploading", "Response");
                        Toast.makeText(Uploading_re.this, "사진 업로드 성공", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Uploading_re.this, Profile_re.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Uploading_re.this, "오류 발생", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<Uploading_User> call, Throwable t) {
                    Log.e("Server Fail", "Error: " + t.getMessage());
                }
            });
        }
    }

    private String imageToBase64(Uri imageUri) {
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, outputStream);
            byte[] imageBytes = outputStream.toByteArray();
            return Base64.encodeToString(imageBytes, Base64.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}