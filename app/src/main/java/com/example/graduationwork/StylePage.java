package com.example.graduationwork;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;


import com.bumptech.glide.Glide;
import com.example.graduationwork.databinding.StylepageBinding;

import java.util.List;


public class StylePage extends AppCompatActivity {
    private StylepageBinding binding;
    private ImageView imageView;


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

    }
}


