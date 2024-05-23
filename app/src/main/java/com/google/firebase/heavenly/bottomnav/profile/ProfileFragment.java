package com.google.firebase.heavenly.bottomnav.profile;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.heavenly.LoginActivity;
import com.google.firebase.heavenly.databinding.FragmentProfileBinding;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;
    private Uri filePath;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        binding.landscapetextView.setText("landscape");
        binding.foodtextView.setText("food");
        binding.architectureView.setText("architecture");
        binding.lifestyletextView.setText("lifestyle");
        binding.lifestyletextView.setText("lifestyle");
        binding.hobbytextView.setText("hobby");
        loadUserLogin();


        binding.profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectimage();
            }
        });
        binding.logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });
        binding.landscape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectimageland();
            }
        });
        binding.food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectimagefood();
            }
        });
        binding.architecture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectimagearche();
            }
        });
        binding.lifestyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectimagelifestyle();
            }
        });
        binding.animals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectimageanim();
            }
        });
        binding.other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectimageother();
            }
        });
        return binding.getRoot();
    }
    ActivityResultLauncher<Intent> pickImageActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode()== Activity.RESULT_OK && result.getData()!=null && result.getData().getData()!=null){
                        filePath = result.getData().getData();
                        try {
                            ImageDecoder.Source source = ImageDecoder.createSource(getContext().getContentResolver(), filePath);
                            Bitmap bitmap = ImageDecoder.decodeBitmap(source);
                            binding.profileImageView.setImageBitmap(bitmap);
                        } catch (IOException e){
                            e.printStackTrace();
                        }
                        uploadProfileImage();
                    }
                }
            });
    ActivityResultLauncher<Intent> pickImageActivityResultLauncherLand = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode()== Activity.RESULT_OK && result.getData()!=null && result.getData().getData()!=null){
                        filePath = result.getData().getData();
                        try {
                            ImageDecoder.Source source = ImageDecoder.createSource(getContext().getContentResolver(), filePath);
                            Bitmap bitmap = ImageDecoder.decodeBitmap(source);
                            binding.landscape.setImageBitmap(bitmap);
                        } catch (IOException e){
                            e.printStackTrace();
                        }
                        uploadlandscapeImage();
                    }
                }
            });
    ActivityResultLauncher<Intent> pickImageActivityResultLauncherFood = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode()== Activity.RESULT_OK && result.getData()!=null && result.getData().getData()!=null){
                        filePath = result.getData().getData();
                        try {
                            ImageDecoder.Source source = ImageDecoder.createSource(getContext().getContentResolver(), filePath);
                            Bitmap bitmap = ImageDecoder.decodeBitmap(source);
                            binding.food.setImageBitmap(bitmap);
                        } catch (IOException e){
                            e.printStackTrace();
                        }
                        uploadfoodImage();
                    }
                }
            });
    ActivityResultLauncher<Intent> pickImageActivityResultLauncheranim = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode()== Activity.RESULT_OK && result.getData()!=null && result.getData().getData()!=null){
                        filePath = result.getData().getData();
                        try {
                            ImageDecoder.Source source = ImageDecoder.createSource(getContext().getContentResolver(), filePath);
                            Bitmap bitmap = ImageDecoder.decodeBitmap(source);
                            binding.animals.setImageBitmap(bitmap);
                        } catch (IOException e){
                            e.printStackTrace();
                        }
                        uploadanimalsImage();
                    }
                }
            });
    ActivityResultLauncher<Intent> pickImageActivityResultLauncherlife = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode()== Activity.RESULT_OK && result.getData()!=null && result.getData().getData()!=null){
                        filePath = result.getData().getData();
                        try {
                            ImageDecoder.Source source = ImageDecoder.createSource(getContext().getContentResolver(), filePath);
                            Bitmap bitmap = ImageDecoder.decodeBitmap(source);
                            binding.lifestyle.setImageBitmap(bitmap);
                        } catch (IOException e){
                            e.printStackTrace();
                        }
                        uploadlifestyleImage();
                    }
                }
            });
    ActivityResultLauncher<Intent> pickImageActivityResultLauncherOther = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode()== Activity.RESULT_OK && result.getData()!=null && result.getData().getData()!=null){
                        filePath = result.getData().getData();
                        try {
                            ImageDecoder.Source source = ImageDecoder.createSource(getContext().getContentResolver(), filePath);
                            Bitmap bitmap = ImageDecoder.decodeBitmap(source);
                            binding.other.setImageBitmap(bitmap);
                        } catch (IOException e){
                            e.printStackTrace();
                        }
                        uploadotherImage();
                    }
                }
            });
    ActivityResultLauncher<Intent> pickImageActivityResultLauncherarch = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode()== Activity.RESULT_OK && result.getData()!=null && result.getData().getData()!=null){
                        filePath = result.getData().getData();
                        try {
                            ImageDecoder.Source source = ImageDecoder.createSource(getContext().getContentResolver(), filePath);
                            Bitmap bitmap = ImageDecoder.decodeBitmap(source);
                            binding.architecture.setImageBitmap(bitmap);
                        } catch (IOException e){
                            e.printStackTrace();
                        }
                        uploadarchitectureImage();
                    }
                }
            });
    private void loadUserLogin(){
        FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.
                getInstance().getCurrentUser().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.child("login").getValue() != null) {
                            String login = snapshot.child("login").getValue().toString();
                            String profileImage = snapshot.child("profileImage").getValue().toString();

                            String landscapeImage = Objects.requireNonNull(snapshot.child("landscapeImage").getValue()).toString();
                            String foodImage = Objects.requireNonNull(snapshot.child("foodImage").getValue()).toString();
                            String architectureImage = Objects.requireNonNull(snapshot.child("architectureImage").getValue()).toString();
                            String otherImage = Objects.requireNonNull(snapshot.child("otherImage").getValue()).toString();
                            String animalsImage = Objects.requireNonNull(snapshot.child("animalsImage").getValue()).toString();
                            String lifestyleImage = Objects.requireNonNull(snapshot.child("lifestyleImage").getValue()).toString();
                            if (!landscapeImage.isEmpty()) {
                                Glide.with(getContext()).load(landscapeImage).into(binding.landscape);
                            }
                            if (!foodImage.isEmpty()) {
                                Glide.with(getContext()).load(foodImage).into(binding.food);
                            }
                            if (!architectureImage.isEmpty()) {
                                Glide.with(getContext()).load(architectureImage).into(binding.architecture);
                            }
                            if (!otherImage.isEmpty()) {
                                Glide.with(getContext()).load(otherImage).into(binding.other);
                            }
                            if (!animalsImage.isEmpty()) {
                                Glide.with(getContext()).load(animalsImage).into(binding.animals);
                            }
                            if (!lifestyleImage.isEmpty()) {
                                Glide.with(getContext()).load(lifestyleImage).into(binding.lifestyle);
                            }
                            if (!profileImage.isEmpty()) {
                                Glide.with(getContext()).load(profileImage).into(binding.profileImageView);
                            }
                            binding.login.setText(login);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
    }

    private void selectimage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        pickImageActivityResultLauncher.launch(intent);
    }
    private void selectimageland(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        pickImageActivityResultLauncherLand.launch(intent);
    }
    private void selectimagefood(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        pickImageActivityResultLauncherFood.launch(intent);
    }
    private void selectimagearche(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        pickImageActivityResultLauncherarch.launch(intent);
    }
    private void selectimageanim(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        pickImageActivityResultLauncheranim.launch(intent);
    }
    private void selectimageother(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        pickImageActivityResultLauncherOther.launch(intent);
    }
    private void selectimagelifestyle(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        pickImageActivityResultLauncherlife.launch(intent);
    }

    private void uploadProfileImage(){
        if (filePath!=null){
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

            FirebaseStorage.getInstance().getReference().child("images/"+uid)
                    .putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(getContext(), "Photo upload complete", Toast.LENGTH_SHORT).show();

                            FirebaseStorage.getInstance().getReference().child("images/"+uid).getDownloadUrl()
                                    .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            FirebaseDatabase.getInstance().getReference().child("Users").
                                                    child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                    .child("profileImage").setValue(uri.toString());
                                        }
                                    });
                        }
                    });
        }
    }
    private void uploadlandscapeImage() {
        if (filePath != null) {
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                FirebaseStorage.getInstance().getReference().child("images/" + uid)
                        .putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(getContext(), "Photo upload complete", Toast.LENGTH_SHORT).show();

                                FirebaseStorage.getInstance().getReference().child("images/" + uid).getDownloadUrl()
                                        .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {
                                                FirebaseDatabase.getInstance().getReference().child("Users").
                                                        child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                        .child("landscapeImage").setValue(uri.toString());
                                            }
                                        });
                            }

                            ;
                        });
        }
    }
    private void uploadfoodImage() {
        if (filePath != null) {
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            FirebaseStorage.getInstance().getReference().child("images/" + uid)
                    .putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(getContext(), "Photo upload complete", Toast.LENGTH_SHORT).show();

                            FirebaseStorage.getInstance().getReference().child("images/" + uid).getDownloadUrl()
                                    .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            FirebaseDatabase.getInstance().getReference().child("Users").
                                                    child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                    .child("foodImage").setValue(uri.toString());
                                        }
                                    });
                        }

                        ;
                    });
        }
    }
    private void uploadarchitectureImage() {
        if (filePath != null) {
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            FirebaseStorage.getInstance().getReference().child("images/" + uid)
                    .putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(getContext(), "Photo upload complete", Toast.LENGTH_SHORT).show();

                            FirebaseStorage.getInstance().getReference().child("images/" + uid).getDownloadUrl()
                                    .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            FirebaseDatabase.getInstance().getReference().child("Users").
                                                    child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                    .child("architectureImage").setValue(uri.toString());
                                        }
                                    });
                        }

                        ;
                    });
        }
    }
    private void uploadanimalsImage() {
        if (filePath != null) {
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            FirebaseStorage.getInstance().getReference().child("images/" + uid)
                    .putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(getContext(), "Photo upload complete", Toast.LENGTH_SHORT).show();

                            FirebaseStorage.getInstance().getReference().child("images/" + uid).getDownloadUrl()
                                    .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            FirebaseDatabase.getInstance().getReference().child("Users").
                                                    child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                    .child("animalsImage").setValue(uri.toString());
                                        }
                                    });
                        }

                        ;
                    });
        }
    }
    private void uploadlifestyleImage() {
        if (filePath != null) {
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            FirebaseStorage.getInstance().getReference().child("images/" + uid)
                    .putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(getContext(), "Photo upload complete", Toast.LENGTH_SHORT).show();

                            FirebaseStorage.getInstance().getReference().child("images/" + uid).getDownloadUrl()
                                    .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            FirebaseDatabase.getInstance().getReference().child("Users").
                                                    child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                    .child("lifestyleImage").setValue(uri.toString());
                                        }
                                    });
                        }

                        ;
                    });
        }
    }
    private void uploadotherImage() {
        if (filePath != null) {
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            FirebaseStorage.getInstance().getReference().child("images/" + uid)
                    .putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(getContext(), "Photo upload complete", Toast.LENGTH_SHORT).show();

                            FirebaseStorage.getInstance().getReference().child("images/" + uid).getDownloadUrl()
                                    .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            FirebaseDatabase.getInstance().getReference().child("Users").
                                                    child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                    .child("otherImage").setValue(uri.toString());
                                        }
                                    });
                        }

                        ;
                    });
        }
    }
}