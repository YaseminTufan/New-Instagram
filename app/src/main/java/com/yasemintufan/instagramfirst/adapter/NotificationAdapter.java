package com.yasemintufan.instagramfirst.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.yasemintufan.instagramfirst.R;
import com.yasemintufan.instagramfirst.fragment.PostDetailFragment;
import com.yasemintufan.instagramfirst.fragment.ProfileFragment;
import com.yasemintufan.instagramfirst.model.Notification;
import com.yasemintufan.instagramfirst.model.Post;
import com.yasemintufan.instagramfirst.model.SearchData;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    private Context mContext;
    private List<Notification> mNotifications;

    public NotificationAdapter(Context mContext, List<Notification> mNotifications) {
        this.mContext = mContext;
        this.mNotifications = mNotifications;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.notification_item,parent,false);
        return new NotificationAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notification notification = mNotifications.get(position);
        getUser(holder.imageProfile,holder.username,notification.getUserid());
        holder.comment.setText(notification.getText());
        if (notification.isPost()) {
            holder.postImage.setVisibility(View.VISIBLE);
            getPostImage(holder.postImage,notification.getPostid());
        }else {
            holder.postImage.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (notification.isPost()) {
                    mContext.getSharedPreferences("PREFS",Context.MODE_PRIVATE).edit().putString("postid",notification.getPostid()).apply();
                    ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new PostDetailFragment()).commit();
                }else {
                    mContext.getSharedPreferences("PROFILE",Context.MODE_PRIVATE).edit().putString("profileId",notification.getUserid()).apply();
                    ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new ProfileFragment()).commit();
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return mNotifications.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageProfile;
        public ImageView postImage;
        public TextView username;
        public TextView comment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageProfile = itemView.findViewById(R.id.image_profile);
            postImage = itemView.findViewById(R.id.post_image);
            username = itemView.findViewById(R.id.username);
            comment = itemView.findViewById(R.id.comment);
        }
    }
    private void getPostImage (ImageView imageView, String postId) {
        FirebaseDatabase.getInstance().getReference().child("Posts").child(postId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Post post = snapshot.getValue(Post.class);
                Picasso.get().load(post.getImageurl()).placeholder(R.mipmap.ic_launcher).into(imageView);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void getUser(ImageView imageView,TextView textView,String userid){
        FirebaseDatabase.getInstance().getReference().child("Users").child(userid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                SearchData searchData = snapshot.getValue(SearchData.class);
                if (searchData.getImageurl().equals("default")) {
                    imageView.setImageResource(R.mipmap.ic_launcher);
                }else {
                    Picasso.get().load(searchData.getImageurl()).into(imageView);
                }
                textView.setText(searchData.getUsername());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
