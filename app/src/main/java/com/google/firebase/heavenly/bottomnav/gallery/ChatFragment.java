package com.google.firebase.heavenly.bottomnav.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.heavenly.chats.Chat;
import com.google.firebase.heavenly.chats.ChatsAdapter;
import com.google.firebase.heavenly.databinding.FragmentChatBinding;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChatFragment extends Fragment {
    private FragmentChatBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentChatBinding.inflate(inflater, container, false);

        loadChats();

        return binding.getRoot();
    }

    private void loadChats(){
        ArrayList<Chat> chats = new ArrayList<>();

        String uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        FirebaseDatabase.getInstance().getReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child("Users").child(uid).child("chats").getValue() == null) return;

                String chatsStr = Objects.requireNonNull(snapshot.child("Users").child(uid).child("chats").getValue()).toString();
                String[] chatsIds = chatsStr.split(",");
                if (chatsIds.length == 0) return;

                for (String chatId : chatsIds) {
                    DataSnapshot chatSnapshot = snapshot.child("Chats").child(chatId);
                    if (chatSnapshot.child("user1").getValue() != null) {
                        String userId1 = Objects.requireNonNull(chatSnapshot.child("user1").getValue()).toString();
                        String userId2 = Objects.requireNonNull(chatSnapshot.child("user2").getValue()).toString();

                        String chatUserId = (uid.equals(userId1)) ? userId2 : userId1;
                        String chatName = Objects.requireNonNull(snapshot.child("Users").child(chatUserId).child("login").getValue()).toString();

                        Chat chat = new Chat(chatId, chatName, userId1, userId2);
                        chats.add(chat);
                    }
                }

                binding.chatsRv.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.chatsRv.setAdapter(new ChatsAdapter(chats));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Failed to get user chats", Toast.LENGTH_SHORT).show();
            }
    });
}
}
