package com.google.firebase.heavenly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.heavenly.databinding.ActivityRegisterBinding;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.emailinput.getEditText().getText().toString().trim().length()==0 ||
                        binding.passwordInput.getEditText().getText().toString().trim().length()==0 ||
                binding.loginInput.getEditText().getText().toString().trim().length()==0){

                } else {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.emailinput.getEditText().getText().toString(),
                            binding.passwordInput.getEditText().getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        HashMap<String, String> userInfo = new HashMap<>();
                                        userInfo.put("email", binding.emailinput.getEditText().getText().toString());
                                        userInfo.put("login", binding.loginInput.getEditText().getText().toString());
                                        userInfo.put("password", binding.passwordInput.getEditText().getText().toString());
                                        userInfo.put("profileImage", "");
                                        userInfo.put("chats", "");

                                        FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth
                                                .getInstance().getCurrentUser().getUid()).setValue(userInfo);

                                        FirebaseDatabase.getInstance().getReference().child("Users")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("foodImage").setValue("");

                                        FirebaseDatabase.getInstance().getReference().child("Users")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("landscapeImage").setValue("");

                                        FirebaseDatabase.getInstance().getReference().child("Users")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("architectureImage").setValue("");

                                        FirebaseDatabase.getInstance().getReference().child("Users")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("lifestyleImage").setValue("");

                                        FirebaseDatabase.getInstance().getReference().child("Users")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("animalsImage").setValue("");

                                        FirebaseDatabase.getInstance().getReference().child("Users")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("otherImage").setValue("");

                                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                    }
                                }
                            });
                }
            }
        });
        binding.ToLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }
}