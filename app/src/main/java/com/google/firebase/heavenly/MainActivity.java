package com.google.firebase.heavenly;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.heavenly.bottomnav.addPhoto.NewChatFragment;
import com.google.firebase.heavenly.bottomnav.gallery.ChatFragment;
import com.google.firebase.heavenly.bottomnav.profile.ProfileFragment;
import com.google.firebase.heavenly.databinding.ActivityMainBinding;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(FirebaseAuth.getInstance().getCurrentUser()==null){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
       }

        getSupportFragmentManager().beginTransaction().replace(binding.fragmentContainer.getId(), new ChatFragment()).commit();
        binding.bottobnav.setSelectedItemId(R.id.gallery);

        Map<Integer, Fragment> fragmentMap= new HashMap<>();

        fragmentMap.put(R.id.profile, new ProfileFragment());
        fragmentMap.put(R.id.gallery, new ChatFragment());
        fragmentMap.put(R.id.add_photo, new NewChatFragment());

        binding.bottobnav.setOnItemSelectedListener(item -> {
            Fragment fragment = fragmentMap.get(item.getItemId());

            getSupportFragmentManager().beginTransaction().replace(binding.fragmentContainer.getId(), fragment).commit();

            return true;
        });
    }
}