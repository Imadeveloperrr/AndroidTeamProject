package com.example.graduationwork;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.bumptech.glide.Glide;
import com.example.graduationwork.databinding.StylepageBinding;




public class StylePage extends AppCompatActivity {
    private StylepageBinding binding;
    private ImageView likeimage;
    private ImageView like2image;
    private TextView likeCountTextView;
    private int likeCount = 0;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = StylepageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // StylePage 액티비티에서 사진을 표시하는 레이아웃을 가져오고,
        // 뷰바인딩을 사용하여 레이아웃 내부의 ImageView를 참조하는 코드

        ViewGroup.LayoutParams layoutParams = binding.StylePagePicture.getLayoutParams();

        // dimens.xml 파일에 있는 이미지뷰의 크기를 지정하는 값을 추가

        layoutParams.width = getResources().getDimensionPixelSize(R.dimen.image_width);
        layoutParams.height = getResources().getDimensionPixelSize(R.dimen.image_height);
        binding.StylePagePicture.setLayoutParams(layoutParams);

        Intent userIntent = getIntent();
        Uploading_User selectedUser = (Uploading_User) userIntent.getSerializableExtra("selectedUser");
        Glide.with(this).load(selectedUser.getImage()).centerCrop().into(binding.exUserImg);
        //binding.exUserImg.setImageResource(R.drawable.ex_kwangjin_img);



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
                likeCount++;
                likeCountTextView.setText(String.valueOf(likeCount));
                toggleImage();
            }
        });
        like2image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 좋아요 카운터 감소
                likeCount--;
                likeCountTextView.setText(String.valueOf(likeCount));
                toggleImage();
            }
        });



    }
    protected void toggleImage() {
        // 클릭 전 이미지뷰가 보이면 클릭 후 이미지뷰로 전환하고, 그 반대의 경우도 마찬가지로 전환.
        if (likeimage.getVisibility() == View.VISIBLE) {
            likeimage.setVisibility(View.GONE);
            like2image.setVisibility(View.VISIBLE);
        } else {
            likeimage.setVisibility(View.VISIBLE);
            like2image.setVisibility(View.GONE);
        }

    }
}