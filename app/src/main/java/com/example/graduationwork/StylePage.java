package com.example.graduationwork;


import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.graduationwork.databinding.StylepageBinding;



public class StylePage extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stylepage);
        // 뷰바인딩 객체 획득
        StylepageBinding binding = StylepageBinding.inflate(getLayoutInflater());
        // 액티비티 화면 출력
        setContentView(binding.getRoot());

        binding.styleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.styleBack.setVisibility(View.VISIBLE);
                // 서브 액티비티로 전환
                setContentView(R.layout.profile_re);
            }
        });
    }
}


