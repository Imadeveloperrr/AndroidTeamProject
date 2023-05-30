package com.example.graduationwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;


import com.example.graduationwork.databinding.FollowingFollowerBinding;



public class Following_followerPage extends AppCompatActivity {

    private FollowingFollowerBinding binding;
    private final int following_fragment = 1;
    private final int follower_fragment = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FollowingFollowerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.followingLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentView(following_fragment);
            }
        });

        binding.followerLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentView(follower_fragment);
            }
        });
        FragmentView(following_fragment);

        binding.followingFollowerBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Following_followerPage.this, Profile_re.class);
                startActivity(intent);
            }
        });
    }

    private void FragmentView(int following_fragment) {
        //FragmentTransactiom를 이용해 프래그먼트를 사용합니다.
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (following_fragment){
            case 1:
                // 첫번 째 프래그먼트 호출
                FollowingFragment fragment1 = new FollowingFragment();
                transaction.replace(R.id.fragment_container, fragment1);
                transaction.commit();
                break;

            case 2:
                // 두번 째 프래그먼트 호출
                FollowerFragment fragment2 = new FollowerFragment();
                transaction.replace(R.id.fragment_container, fragment2);
                transaction.commit();
                break;
        }

    }
}
