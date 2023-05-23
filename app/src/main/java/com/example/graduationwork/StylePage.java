package com.example.graduationwork;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.bumptech.glide.Glide;
import com.example.graduationwork.databinding.StylepageBinding;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StylePage extends AppCompatActivity {
    private StylepageBinding binding;
    private ImageView likeimage;
    private ImageView like2image;
    private TextView likeCountTextView;
    private int likeCount = 0;

    private StylePageService apiInterface;
    private Uploading_User selectedUser;

    private List<String> like_people = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = StylepageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        apiInterface = ApiClient.getClient().create(StylePageService.class);
        // StylePage 액티비티에서 사진을 표시하는 레이아웃을 가져오고,
        // 뷰바인딩을 사용하여 레이아웃 내부의 ImageView를 참조하는 코드

        ViewGroup.LayoutParams layoutParams = binding.StylePagePicture.getLayoutParams();

        // dimens.xml 파일에 있는 이미지뷰의 크기를 지정하는 값을 추가

        layoutParams.width = getResources().getDimensionPixelSize(R.dimen.image_width);
        layoutParams.height = getResources().getDimensionPixelSize(R.dimen.image_height);
        binding.StylePagePicture.setLayoutParams(layoutParams);

        Intent userIntent = getIntent();
        selectedUser = (Uploading_User) userIntent.getSerializableExtra("selectedUser");
        Glide.with(this).load(selectedUser.getImage()).into(binding.exUserImg);
        //binding.exUserImg.setImageResource(R.drawable.ex_kwangjin_img);

        post_like(0);

        binding.styleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StylePage.this, Profile_re.class);
                startActivity(intent);
            }
        });

        binding.StylePageCommentsEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StylePage.this, CommentsPage.class);
                startActivity(intent);
            }
        });

        // 좋아요 기능 구현
        likeimage = binding.likeImage;
        like2image = binding.like2Image;

        // 좋아요 카운터
        likeCountTextView = binding.likeCountTextView;


        // 이미지뷰 클릭 리스너 설정
        likeimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 좋아요 카운터 증가
                post_like(1);
            }
        });
        like2image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 좋아요 카운터 감소
                post_like(2);
            }
        });



    }
    protected void toggleImage() {
        // 클릭 전 이미지뷰가 보이면 클릭 후 이미지뷰로 전환하고, 그 반대의 경우도 마찬가지로 전환.
        //Login_User user = Login_User.getInstance();
        //if (like_people.contains(user.getEmail())) {
        if (like_people.contains("ddooochii@gmail.com")) {
            likeimage.setVisibility(View.GONE);
            like2image.setVisibility(View.VISIBLE);
        } else {
            likeimage.setVisibility(View.VISIBLE);
            like2image.setVisibility(View.GONE);
        }
//        if (likeimage.getVisibility() == View.VISIBLE) {
//            likeimage.setVisibility(View.GONE);
//            like2image.setVisibility(View.VISIBLE);
//        } else {
//            likeimage.setVisibility(View.VISIBLE);
//            like2image.setVisibility(View.GONE);
//        }
    }

    private void post_like(int type) { // 0 실시간확인, 1 증가, 2감소
        Login_User user = Login_User.getInstance();
        //String user_email = user.getEmail();
        String like_email = "ddooochii@gmail.com";
        int post_id = selectedUser.getId();
        int post_like = selectedUser.getPostlike();
        if(type == 1)
            post_like++;
        else if(type == 2)
            post_like--;

        Call<List<JsonObject>> call = apiInterface.likeUser(post_id, like_email, post_like);
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                if (response.isSuccessful()) {
                    Log.d("postlike", "Success");
                    List<JsonObject> responseBody = response.body();
                    JsonObject firstobject = response.body().get(0); // 첫번째 인덱스에서 postlike 갖고오고
                    selectedUser.setPostlike(firstobject.get("postlike").getAsInt());
                    selectedUser.setId(firstobject.get("id").getAsInt());
                    for (JsonObject jsonObject : responseBody) {
                        like_people.add(jsonObject.get("like_emial").getAsString());
                    }
                    likeCountTextView.setText(String.valueOf(selectedUser.getPostlike()));
                    toggleImage();
                }
            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {

            }
        });
    }


}