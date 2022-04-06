package com.yasemintufan.instagramfirst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.yasemintufan.instagramfirst.fragment.HomeFragment;
import com.yasemintufan.instagramfirst.fragment.NotificationFragment;
import com.yasemintufan.instagramfirst.fragment.ProfileFragment;
import com.yasemintufan.instagramfirst.fragment.SearchFragment;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private Fragment selectorFragment;


     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         bottomNavigationView = findViewById(R.id.bottom_navigation);

         bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
             @Override
             public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                 switch (menuItem.getItemId()){
                     case R.id.nav_home :
                         selectorFragment = new HomeFragment();
                         break;

                     case R.id.nav_search :
                         selectorFragment = new SearchFragment();
                         break;

                     case R.id.nav_add :
                         selectorFragment = null;
                         startActivity(new Intent(MainActivity.this , PostActivity.class));
                         break;

                     case R.id.nav_heart :
                         selectorFragment = new NotificationFragment();
                         break;

                     case R.id.nav_profile :
                         selectorFragment = new ProfileFragment();
                         break;
                 }

                 if (selectorFragment != null){
                     getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout , selectorFragment).commit();
                 }

                 return  true;

             }
         });

         Bundle intent = getIntent().getExtras();
         if (intent != null) {
             String profileId = intent.getString("publisherId");

             getSharedPreferences("PROFILE", MODE_PRIVATE).edit().putString("profileId", profileId).apply();

             getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new ProfileFragment()).commit();
             bottomNavigationView.setSelectedItemId(R.id.nav_profile);
         } else {
             getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout , new HomeFragment()).commit();
         }
     }

 }







