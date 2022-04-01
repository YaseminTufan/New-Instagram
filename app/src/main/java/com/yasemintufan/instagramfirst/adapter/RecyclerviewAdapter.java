package com.yasemintufan.instagramfirst.adapter;

import static com.yasemintufan.instagramfirst.R.drawable.bottom_sheet_drawable;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.yasemintufan.instagramfirst.ItemAnimation;
import com.yasemintufan.instagramfirst.MainActivity;
import com.yasemintufan.instagramfirst.R;
import com.yasemintufan.instagramfirst.UserDetails;
import com.yasemintufan.instagramfirst.model.MainData;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.RecyclerviewHolder> {
    Context context;
    List<MainData> mainDataList;
    private boolean isClick = false;
    private FirebaseUser firebaseUser;


    public RecyclerviewAdapter(Context context, List<MainData> mainDataList) {
        this.context = context;
        this.mainDataList = mainDataList;
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    }
    @NonNull
    @Override
    public RecyclerviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_row,parent,false);
        return new RecyclerviewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerviewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.userComment.setText(mainDataList.get(position).getUser().getUserComment());
        holder.userImage.setImageResource(mainDataList.get(position).getImageUrl());
        holder.userName.setText(mainDataList.get(position).getUser().getUserName());
        holder.userCircle.setImageResource(mainDataList.get(position).getUser().getUserCircle());
        holder.userLocation.setText(mainDataList.get(position).getUser().getUserLocation());
        ItemAnimation.animateBottomUp(holder.itemView,position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UserDetails.class);
                intent.putExtra("username", mainDataList.get(position).getUser().getUserName());
                intent.putExtra("usercomment",mainDataList.get(position).getUser().getUserComment());
                context.startActivity(intent);
            }
        });
        holder.userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"User Name Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        holder.bottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialog();
            }
        });

        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isClick) {
                    holder.imageButton.setImageResource(R.drawable.kk);
                    isClick = false;
                } else {
                    holder.imageButton.setImageResource(R.drawable.kalpimage);
                    isClick = true;
                }
            }
        });

        if (mainDataList.get(position).getUser().getUserLocation().contentEquals("")) {
            holder.userLocation.setVisibility(View.INVISIBLE);
        } else {
            holder.userLocation.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public int getItemCount() {
        return mainDataList.size();
    }

    public static final class RecyclerviewHolder extends RecyclerView.ViewHolder{
        ImageView userImage;
        EditText userComment;
        TextView userName;
        ImageView imageButton;
        CircleImageView userCircle;
        TextView userLocation;
        ImageView bottomSheet;
        public  RecyclerviewHolder(@NonNull View itemView) {
            super(itemView);
            userImage = itemView.findViewById(R.id.userImage);
            userComment= itemView.findViewById(R.id.userComment);
            userName = itemView.findViewById(R.id.userName);
            imageButton = itemView.findViewById(R.id.imageViewLikeIcon);
            userCircle = itemView.findViewById(R.id.user_circle);
            userLocation = itemView.findViewById(R.id.location_text);
            bottomSheet = itemView.findViewById(R.id.imageViewThreeDot);
        }
    }

    private void showBottomSheetDialog() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context,R.style.BottomSheetDialogTheme);
        bottomSheetDialog.setContentView(R.layout.layout_bottom_sheet);
        bottomSheetDialog.findViewById(R.id.sikayetSheet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "sikayettttt", Toast.LENGTH_LONG).show();
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.show();
    }

    }


