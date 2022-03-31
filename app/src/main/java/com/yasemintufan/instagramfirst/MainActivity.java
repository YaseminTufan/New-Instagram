package com.yasemintufan.instagramfirst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.yasemintufan.instagramfirst.adapter.RecyclerviewAdapter;
import com.yasemintufan.instagramfirst.model.MainData;
import com.yasemintufan.instagramfirst.model.User;

import java.util.ArrayList;
import java.util.List;

 public class MainActivity extends AppCompatActivity {
     Button buttonHome, buttonSearch, buttonNotification, buttonProfile, buttonPost;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         buttonHome = findViewById(R.id.buttonHome);
         buttonSearch = findViewById(R.id.buttonSearch);
         buttonNotification = findViewById(R.id.buttonNotification);
         buttonProfile = findViewById(R.id.buttonProfile);
         buttonPost = findViewById(R.id.buttonPost);

         if(savedInstanceState == null) {
             HomeFragment homeFragment = new HomeFragment();
             getSupportFragmentManager().
                     beginTransaction().
                     replace(R.id.frameLayout, homeFragment).
                     commit();
         }

         buttonHome.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 HomeFragment homeFragment = new HomeFragment();
                 goToFragment(homeFragment);
             }
         });

         buttonSearch.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 SearchFragment searchFragment = new SearchFragment();
                 goToFragment(searchFragment);
             }
         });

         buttonNotification.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 NotificationFragment notificationFragment = new NotificationFragment();
                 goToFragment(notificationFragment);
             }
         });
         buttonProfile.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 ProfileFragment profileFragment = new ProfileFragment();
                 goToFragment(profileFragment);
             }
         });
         buttonPost.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 startActivity(new Intent(MainActivity.this,PostActivity.class));
             }
         });
     }
     protected void goToFragment(Fragment fragment) {
         FragmentManager fragmentManager = getSupportFragmentManager();
         FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
         fragmentTransaction.replace(R.id.frameLayout, fragment);
         fragmentTransaction.commit();
     }

 }







