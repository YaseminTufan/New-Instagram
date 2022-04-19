package com.yasemintufan.instagramfirst.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hendraanggrian.appcompat.widget.SocialTextView;
import com.squareup.picasso.Picasso;
import com.yasemintufan.instagramfirst.CommentActivity;
import com.yasemintufan.instagramfirst.FollowersActivity;
import com.yasemintufan.instagramfirst.R;
import com.yasemintufan.instagramfirst.fragment.PostDetailFragment;
import com.yasemintufan.instagramfirst.fragment.ProfileFragment;
import com.yasemintufan.instagramfirst.model.Post;
import com.yasemintufan.instagramfirst.model.SearchData;

import java.util.HashMap;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private Context mContext;
    private List<Post> mPosts;

    private FirebaseUser firebaseUser;

    public PostAdapter(Context mContext, List<Post> mPosts) {
        this.mContext = mContext;
        this.mPosts = mPosts;
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_row,parent,false);
      return new PostAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post =mPosts.get(position);
        Picasso.get().load(post.getImageurl()).into(holder.postImage);
        holder.description.setText(post.getDescription());
        FirebaseDatabase.getInstance().getReference().child("Users").child(post.getPubliser()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                SearchData searchData =snapshot.getValue(SearchData.class);
                if(searchData.getImageurl().equals("default")) {
                    holder.imageProfile.setImageResource(R.mipmap.ic_launcher);
                }else {
                    Picasso.get().load(searchData.getImageurl()).placeholder(R.mipmap.ic_launcher).into(holder.imageProfile);

                }
                holder.username.setText(searchData.getUsername());
                holder.author.setText(searchData.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        isLiked(post.getPostid(),holder.like);
        noOfLikes(post.getPostid(),holder.noOfLikes);
        getComments(post.getPostid(),holder.noOfComments);
        isSaved(post.getPostid(),holder.save);
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             if (holder.like.getTag().equals("like")) {
                 FirebaseDatabase.getInstance().getReference().child("Likes")
                         .child(post.getPostid()).child(firebaseUser.getUid()).setValue(true);
                 addNotification(post.getPostid(),post.getPubliser());
             }else {
                 FirebaseDatabase.getInstance().getReference().child("Likes")
                         .child(post.getPostid()).child(firebaseUser.getUid()).removeValue();

             }
            }
        });
        holder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, CommentActivity.class);
                intent.putExtra("postId",post.getPostid());
                intent.putExtra("authorId",post.getPubliser());
                mContext.startActivity(intent);
            }
        });
        holder.noOfComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, CommentActivity.class);
                intent.putExtra("postId",post.getPostid());
                intent.putExtra("authorId",post.getPubliser());
                mContext.startActivity(intent);
            }
        });
        holder.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.save.getTag().equals("save")) {
                    FirebaseDatabase.getInstance().getReference().child("Saves")
                            .child(firebaseUser.getUid()).child(post.getPostid()).setValue(true);
                }else {
                    FirebaseDatabase.getInstance().getReference().child("Saves")
                            .child(firebaseUser.getUid()).child(post.getPostid()).removeValue();

                }
            }
        });
        holder.imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.getSharedPreferences("PROFILE",Context.MODE_PRIVATE)
                        .edit().putString("profileid",post.getPubliser()).apply();
                ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout,new ProfileFragment()).commit();
            }
        });
        holder.username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.getSharedPreferences("PROFILE",Context.MODE_PRIVATE)
                        .edit().putString("profileid",post.getPubliser()).apply();
                ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout,new ProfileFragment()).commit();
            }
        });
        holder.author.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.getSharedPreferences("PROFILE",Context.MODE_PRIVATE)
                        .edit().putString("profileid",post.getPubliser()).apply();
                ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout,new ProfileFragment()).commit();
            }
        });
        holder.postImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.getSharedPreferences("PREFS",Context.MODE_PRIVATE).edit().putString("postid",post.getPostid()).apply();
                ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout,new PostDetailFragment()).commit();
            }
        });
        holder.noOfLikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, FollowersActivity.class);
                intent.putExtra("id",post.getPubliser());
                intent.putExtra("title","followings");
                mContext.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageProfile;
        public ImageView postImage;
        public ImageView like;
        public ImageView comment;
        public ImageView save;
        public ImageView more;
        public TextView username;
        public TextView noOfLikes;
        public TextView author;
        public TextView noOfComments;
        SocialTextView description;


        @SuppressLint("CutPasteId")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageProfile = itemView.findViewById(R.id.image_profile);
            postImage = itemView.findViewById(R.id.post_image);
            like = itemView.findViewById(R.id.like);
            comment = itemView.findViewById(R.id.comment);
            save = itemView.findViewById(R.id.save);
            more = itemView.findViewById(R.id.more);
            username = itemView.findViewById(R.id.username);
            noOfLikes = itemView.findViewById(R.id.no_of_likes);
            author = itemView.findViewById(R.id.author);
            noOfComments = itemView.findViewById(R.id.no_of_comments);
            description = itemView.findViewById(R.id.description);
        }
    }
    private void isSaved (String postId,ImageView image){
        FirebaseDatabase.getInstance().getReference().child("Saves").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(postId).exists()) {
                    image.setImageResource(R.drawable.ic_save_black);
                    image.setTag("saved");
                }else {
                    image.setImageResource(R.drawable.ic_save_grey);
                    image.setTag("save");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void isLiked (String postId,ImageView imageView) {
        FirebaseDatabase.getInstance().getReference().child("Likes").child(postId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(firebaseUser.getUid()).exists()) {
                    imageView.setImageResource(R.drawable.ic_baseline_favorite_24);
                    imageView.setTag("liked");
                }else {
                    imageView.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                    imageView.setTag("like");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void noOfLikes (String postId, TextView text) {
        FirebaseDatabase.getInstance().getReference().child("Likes").child(postId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                text.setText(snapshot.getChildrenCount() + "likes");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void getComments (String postId,TextView text) {
        FirebaseDatabase.getInstance().getReference().child("Comments").child(postId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                text.setText("View All" + snapshot.getChildrenCount() + " Comments");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void addNotification (String postId , String publiserId) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("userid",publiserId);
        map.put("text","liked your post.");
        map.put("postid",postId);
        map.put("isPost",true);
        FirebaseDatabase.getInstance().getReference().child("Notifications").child(firebaseUser.getUid()).push().setValue(map);
    }
}
