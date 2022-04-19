package com.yasemintufan.instagramfirst.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.skydoves.balloon.ArrowOrientation;
import com.skydoves.balloon.Balloon;
import com.skydoves.balloon.BalloonAnimation;
import com.yasemintufan.instagramfirst.R;
import com.yasemintufan.instagramfirst.adapter.PostAdapter;
import com.yasemintufan.instagramfirst.model.Post;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerViewPosts;
    private PostAdapter postAdapter;
    private List<Post> postList;
    private List<String> followingList;
    private ImageView share;
    Balloon balloon;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerViewPosts = view.findViewById(R.id.recycler_view_posts);
        recyclerViewPosts.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
       linearLayoutManager.setStackFromEnd(true);
       linearLayoutManager.setReverseLayout(true);
      recyclerViewPosts.setLayoutManager(linearLayoutManager);
        postList = new ArrayList<>();
       postAdapter = new PostAdapter(getContext(),postList);
       recyclerViewPosts.setAdapter(postAdapter);
       followingList = new ArrayList<>();
       checkFollowingUsers();
       share = view.findViewById(R.id.share);
       balloon = new Balloon.Builder(getContext())
               .setArrowSize(7)
               .setArrowOrientation(ArrowOrientation.TOP)
               .setIsVisibleArrow(true)
               .setWidthRatio(0.60f)
               .setHeight(65)
               .setTextSize(15f)
               .setArrowPosition(0.90f)
               .setCornerRadius(10f)
               .setText("This is simple balloon message")
               .setTextColor(ContextCompat.getColor(getContext(),R.color.white))
               .setBackgroundColor(ContextCompat.getColor(getContext(),R.color.purple_200))
               .setBalloonAnimation(BalloonAnimation.ELASTIC)
               .build();
       share.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               balloon.showAlignBottom(share);
               new Handler().postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       balloon.dismiss();
                   }
               },2000);
           }
       });
        return view;
    }

    private void checkFollowingUsers() {
        FirebaseDatabase.getInstance().getReference().child("Follow").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("following").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                followingList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    followingList.add(dataSnapshot.getKey());
                }
                followingList.add(FirebaseAuth.getInstance().getCurrentUser().getUid());
                readPosts();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void readPosts() {
        FirebaseDatabase.getInstance().getReference().child("Posts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                postList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Post post = dataSnapshot.getValue(Post.class);
                    for (String id : followingList){
                       if(post.getPubliser().equals(id)){
                            postList.add(post);
                        }
                    }
                }
                postAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}