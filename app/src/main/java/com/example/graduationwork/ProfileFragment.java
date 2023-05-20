package com.example.graduationwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProfileFragment extends Fragment {

    private List<Uploading_User> userList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3)); // 3열로 나누는 그리드 레이아웃 사용
        recyclerView.setAdapter(new ImageAdapter(userList));
        recyclerView.setNestedScrollingEnabled(false);
        return rootView;
    }

    public void setUserList(List<Uploading_User> userList) {
        this.userList = userList;
    }

    public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
        private List<Uploading_User> userList;

        public ImageAdapter(List<Uploading_User> userList) {
            this.userList = userList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ImageView imageView = new ImageView(parent.getContext());
            int imageSizeInPixels = (int) (137 * parent.getContext().getResources().getDisplayMetrics().density);
            imageView.setLayoutParams(new RecyclerView.LayoutParams(imageSizeInPixels, imageSizeInPixels));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            return new ViewHolder(imageView);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Uploading_User user = userList.get(position);
            Glide.with(holder.itemView.getContext()).load(user.getImage()).into(holder.imageView);

            holder.imageView.setOnClickListener(view -> {
                Intent intent = new Intent(getContext(), StylePage.class);
                intent.putExtra("selectedUser", user);
                startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return userList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView imageView;

            public ViewHolder(@NonNull ImageView itemView) {
                super(itemView);
                imageView = itemView;
            }
        }
    }
}