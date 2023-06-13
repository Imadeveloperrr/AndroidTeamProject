package com.example.graduationwork;


import android.animation.ObjectAnimator;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.exifinterface.media.ExifInterface;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.example.graduationwork.databinding.UploadingReBinding;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Uploading_re extends AppCompatActivity {
    View View;
    private UploadingReBinding binding;
    private ApiService apiInterface;
    private AmazonS3 s3Client;
    private String bucketName = "sunghobucket";
    private String region = "ap-northeast-2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = UploadingReBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ImageView imageView2 = findViewById(R.id.check);
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView2, "alpha", 1f, 0f, 1f);
        animator.setDuration(350);
        animator.setRepeatCount(0);
        animator.start();

        apiInterface = ApiClient.getClient().create(ApiService.class);

        binding.uploadingReBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent intent = new Intent(Uploading_re.this, Homepage_re.class);
                startActivity(intent);
            }
        });

        binding.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent intent = new Intent(Uploading_re.this, Homepage_re.class);
                startActivity(intent);
            }
        });

        binding.serch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent intent = new Intent(Uploading_re.this, Search.class);
                startActivity(intent);
            }
        });

         binding.my.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(android.view.View view) {
                 Intent intent = new Intent(Uploading_re.this, Profile_re.class);
                 startActivity(intent);
             }
         });

        s3Client = new AmazonS3Client(new BasicAWSCredentials("AKIAYGFTFCC7AI7AODD6", "RgA2qnuUzhSQCjfSh3Zwz9sVN+UtojDCtuaUBu/M"));
        s3Client.setRegion(Region.getRegion(Regions.fromName(region)));

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

            String path = getPath(imageUri);

            TransferUtility transferUtility = TransferUtility.builder().s3Client(s3Client).context(this).build();
            String key = "images/" + new File(path).getName();
            TransferObserver uploadObserver = transferUtility.upload(bucketName, key, new File(path));

            uploadObserver.setTransferListener(new TransferListener() {
                @Override
                public void onStateChanged(int id, TransferState state) {
                    if (TransferState.COMPLETED == state) {
                        String url = "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + key;
                        uploadImageUrl(url);
                    }
                }

                @Override
                public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {

                }

                @Override
                public void onError(int id, Exception ex) {

                }
            });
        }
    }

    private void uploadImageUrl(String imageUrl) {
        Login_User user = Login_User.getInstance();
        //String user_email = user.getEmail();
        String user_email = "ddooochii@gmail.com";
        String title = "테스트";
        Call<Uploading_User> call = apiInterface.insertUploading(user_email, title, imageUrl);
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

    private String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String path = cursor.getString(column_index);
        cursor.close();
        return path;
    }
}