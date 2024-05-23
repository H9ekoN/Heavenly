package com.google.firebase.heavenly.users;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.heavenly.R;
import com.google.firebase.heavenly.utils.ChatUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class UsersAdapter extends RecyclerView.Adapter<UserViewHolder> {
    private ArrayList<User> users = new ArrayList<>();
    public UsersAdapter(ArrayList<User> users){
        this.users = users;
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item_rv, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = users.get(position);

        holder.username.setText(user.username);

        if(!(user.Imageprofile.isEmpty())){
            Objects.requireNonNull(Glide.with(holder.itemView.getContext()).load(user.Imageprofile).into(holder.profileImage));
        }
        if(!(user.architecture.isEmpty())){
            Objects.requireNonNull(Glide.with(holder.itemView.getContext()).load(user.architecture).into(holder.architectureImage));
        }
        if(!(user.animals.isEmpty())){
            Objects.requireNonNull(Glide.with(holder.itemView.getContext()).load(user.animals).into(holder.animalsImage));
        }
        if(!(user.other.isEmpty())){
            Objects.requireNonNull(Glide.with(holder.itemView.getContext()).load(user.other).into(holder.otherImage));
        }
        if(!(user.imagelandscape.isEmpty())){
            Objects.requireNonNull(Glide.with(holder.itemView.getContext()).load(user.imagelandscape).into(holder.landscapeImage));
        }
        if(!(user.imagefood.isEmpty())){
            Objects.requireNonNull(Glide.with(holder.itemView.getContext()).load(user.imagefood).into(holder.foodImage));
        }
        if(!(user.imageLifeStyle.isEmpty())){
            Objects.requireNonNull(Glide.with(holder.itemView.getContext()).load(user.imageLifeStyle).into(holder.lifestyleImage));
        }
        holder.itemView.setOnClickListener(v ->{
            ChatUtil.createChat(users.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }


}
