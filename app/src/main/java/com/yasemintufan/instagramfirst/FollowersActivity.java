package com.yasemintufan.instagramfirst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yasemintufan.instagramfirst.adapter.RecyclerviewSearchAdapter;
import com.yasemintufan.instagramfirst.model.SearchData;

import java.util.ArrayList;
import java.util.List;

public class FollowersActivity extends AppCompatActivity {
    private String id;
    private String title;
    private List<String> idList;

    private RecyclerView recyclerView;
    private RecyclerviewSearchAdapter recyclerviewSearchAdapter;
    private List<SearchData> mSearchData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        title = intent.getStringExtra("title");

        Toolbar toolbar =findViewById(R.id.toolbar);

        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSearchData = new ArrayList<>();
        recyclerviewSearchAdapter = new RecyclerviewSearchAdapter(this,mSearchData,false);
        idList = new ArrayList<>();

        switch (title) {
            case "followers" :
                getFollowers();
                break;
            case "followings" :
                getFollowings();
                break;
            case "likes" :
                getLikes();
                break;
        }
    }

    private void getFollowers() {
        FirebaseDatabase.getInstance().getReference().child("Follow").child(id).child("followers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                idList.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    idList.add(dataSnapshot.getKey());
                }
                showUsers();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void getFollowings() {
        FirebaseDatabase.getInstance().getReference().child("Follow").child(id).child("following").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                idList.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    idList.add(dataSnapshot.getKey());
                }
                showUsers();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void getLikes() {
        FirebaseDatabase.getInstance().getReference().child("Likes").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                idList.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    idList.add(dataSnapshot.getKey());
                }
                showUsers();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void showUsers() {
        FirebaseDatabase.getInstance().getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mSearchData.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    SearchData searchData = dataSnapshot.getValue(SearchData.class);

                    for (String id : idList) {
                        if (searchData.getId().equals(id)) {
                            mSearchData.add(searchData);

                        }
                    }
                }
                Log.d("list f",mSearchData.toString());
                recyclerviewSearchAdapter.notifyDataSetChanged();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}