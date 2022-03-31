package com.yasemintufan.instagramfirst.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.yasemintufan.instagramfirst.R;
import com.yasemintufan.instagramfirst.model.SearchData;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerviewSearchAdapter extends RecyclerView.Adapter<RecyclerviewSearchAdapter.ViewHolder>{
    private Context mContext;
    private List<SearchData> mSearchData;
    private boolean isFargment;
    private FirebaseUser firebaseUser;

    public RecyclerviewSearchAdapter(Context mContext, List<SearchData> mSearchData, boolean isFargment) {
        this.mContext = mContext;
        this.mSearchData = mSearchData;
        this.isFargment = isFargment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.photos_search,parent,false);
        return new RecyclerviewSearchAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        SearchData searchData = mSearchData.get(position);
        holder.btnFollow.setVisibility(View.VISIBLE);
        holder.username.setText(searchData.getUsername());
        holder.fullname.setText(searchData.getName());
        Picasso.get().load(searchData.getImageurl()).placeholder(R.mipmap.ic_launcher).into(holder.imageProfile);
        isFollowed(searchData.getId(),holder.btnFollow);
        if(searchData.getId().equals(firebaseUser.getUid())) {
            holder.btnFollow.setVisibility(View.GONE);
        }
    }
    private void isFollowed(String id, Button btnFollow) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Follow").child(firebaseUser.getUid())
                .child("following");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(id).exists())
                    btnFollow.setText("Following");
                else
                    btnFollow.setText("follow");
                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    @Override
    public int getItemCount() {
        return mSearchData.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView imageProfile;
        public TextView username;
        public TextView fullname;
        public Button btnFollow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageProfile = itemView.findViewById(R.id.image_profile);
            username = itemView.findViewById(R.id.username);
            fullname = itemView.findViewById(R.id.fullname);
            btnFollow = itemView.findViewById(R.id.btn_follow);
        }
    }
    }


