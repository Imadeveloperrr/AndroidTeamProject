package com.example.graduationwork;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.exifinterface.media.ExifInterface;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.graduationwork.databinding.ProfileReBinding;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
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
                    ProfileFragment profileFragment = new ProfileFragment();
                    profileFragment.setUserList(userList); // Set the userList in the fragment.

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.profile_re_show_img, profileFragment);
                    transaction.commit();
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

    }
    private Bitmap setImageFromBase64(String base64Image) {
        // Base64 형식의 이미지를 디코딩
        byte[] decodedBytes = Base64.decode(base64Image, Base64.DEFAULT);
        // 디코딩된 바이트를 비트맵으로 변환
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    private void onImageViewClicked(int position) {
        // 여기에 이미지뷰를 클릭했을 때 수행할 작업을 작성합니다.
        Uploading_User selectedUser = userList.get(position);
        Intent intent = new Intent(Profile_re.this, StylePage.class);
        intent.putExtra("selectedUser", selectedUser);
        startActivity(intent);
    }

    public static class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
        interface OnItemClickListener {
            void onItemClick(int position);
        }
        private Context mContext;
        private ArrayList<Bitmap> mImageList;
        private OnItemClickListener listener;

        public ImageAdapter(Context context, ArrayList<Bitmap> imageList) {
            mContext = context;
            mImageList = imageList;
        }
        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ImageView imageView = new ImageView(mContext);
            int imageSizeInPixels = (int) (137 * mContext.getResources().getDisplayMetrics().density);
            imageView.setLayoutParams(new RecyclerView.LayoutParams(imageSizeInPixels, imageSizeInPixels));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            return new ViewHolder(imageView);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.imageView.setImageBitmap(mImageList.get(position));
        }

        @Override
        public int getItemCount() {
            return mImageList.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView imageView;

            public ViewHolder(@NonNull ImageView itemView) {
                super(itemView);
                imageView = itemView;
            }
        }
    }


}
