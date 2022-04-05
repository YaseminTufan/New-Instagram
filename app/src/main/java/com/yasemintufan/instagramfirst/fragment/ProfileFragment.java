package com.yasemintufan.instagramfirst.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.yasemintufan.instagramfirst.R;
import com.yasemintufan.instagramfirst.StartActivity;

public class ProfileFragment extends Fragment {
    ImageView image_profile, options, add;
    TextView usernameprofile, posts, followers, following, bio;
    Button edit_profile;
    ImageButton my_photos, video_photos, saved_photos;
    FirebaseAuth mAuth;


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
        mAuth = FirebaseAuth.getInstance();
        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme);
                bottomSheetDialog.setContentView(R.layout.signout_bottomsheet);
                bottomSheetDialog.show();
                bottomSheetDialog.findViewById(R.id.signout).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bottomSheetDialog.dismiss();
                        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                        alert.setTitle("SİGN OUT");
                        alert.setMessage("Çıkmak istediğine emin misin?");
                        alert.setCancelable(false);

                        alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(getContext(), StartActivity.class));
                                mAuth.getInstance().signOut();
                            }
                        });
                        alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        alert.show();
                    }
                });
            }
        });
        return view;
    }
}



