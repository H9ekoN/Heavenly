package com.google.firebase.heavenly.users;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.heavenly.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserViewHolder extends RecyclerView.ViewHolder {

    CircleImageView profileImage;
    TextView username;
    CircleImageView animalsImage;
    CircleImageView architectureImage;
    CircleImageView foodImage;
    CircleImageView landscapeImage;
    CircleImageView lifestyleImage;
    CircleImageView otherImage;
    public UserViewHolder(@NonNull View itemView) {
        super(itemView);

        profileImage = itemView.findViewById(R.id.profile_image_view);
        username = itemView.findViewById(R.id.login);
        animalsImage = itemView.findViewById(R.id.animals);
        architectureImage = itemView.findViewById(R.id.architecture);
        foodImage = itemView.findViewById(R.id.imagefood);
        landscapeImage = itemView.findViewById(R.id.imagelandscape);
        lifestyleImage = itemView.findViewById(R.id.imageLifeStyle);
        otherImage = itemView.findViewById(R.id.other);
    }
}
