package com.yasemintufan.instagramfirst;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {
    ImageView image_profile, options, add;
    TextView usernameprofile, posts, followers, following, bio;
    Button edit_profile;
    ImageButton my_photos,video_photos, saved_photos;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        image_profile = view.findViewById(R.id.image_profile);
        options = view.findViewById(R.id.options);
        add = view.findViewById(R.id.add);
        usernameprofile = view.findViewById(R.id.usernameprofile);
        posts = view.findViewById(R.id.posts);
        followers = view.findViewById(R.id.followers);
        following = view.findViewById(R.id.following);
        bio = view.findViewById(R.id.bio);
        edit_profile = view.findViewById(R.id.edit_profile);
        my_photos = view.findViewById(R.id.my_photos);
        video_photos = view.findViewById(R.id.video_photos);
        saved_photos = view.findViewById(R.id.saved_photos);



        return view;
    }
}


