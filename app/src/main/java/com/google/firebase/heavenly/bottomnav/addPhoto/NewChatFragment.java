package com.google.firebase.heavenly.bottomnav.addPhoto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.heavenly.databinding.FragmentNewChatBinding;
import com.google.firebase.heavenly.users.User;
import com.google.firebase.heavenly.users.UsersAdapter;

import java.util.ArrayList;

public class NewChatFragment extends Fragment {
    private FragmentNewChatBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNewChatBinding.inflate(inflater, container, false);

        loadUsers();

        return binding.getRoot();
    }

    private void loadUsers(){
        ArrayList<User> users = new ArrayList<User>();
        FirebaseDatabase.getInstance().getReference().child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot userSnapshot : snapshot.getChildren()){
                    if (userSnapshot.getKey().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                        continue;
                    }
                    String uid = userSnapshot.getKey();

                    String login = userSnapshot.child("login").getValue().toString();
                    String profileImage = userSnapshot.child("profileImage").getValue().toString();

                    String animalsImage = userSnapshot.child("animalsImage").getValue().toString();
                    String architectureImage = userSnapshot.child("architectureImage").getValue().toString();
                    String foodImage = userSnapshot.child("foodImage").getValue().toString();
                    String landscapeImage = userSnapshot.child("landscapeImage").getValue().toString();
                    String lifestyleImage = userSnapshot.child("lifestyleImage").getValue().toString();
                    String otherImage = userSnapshot.child("otherImage").getValue().toString();

                    users.add(new User(uid, login, profileImage, lifestyleImage, foodImage, otherImage, landscapeImage, architectureImage, animalsImage));
                }

                binding.usersRv.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.usersRv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
                binding.usersRv.setAdapter(new UsersAdapter(users));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
