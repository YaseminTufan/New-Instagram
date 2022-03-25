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
    List<MainData> filteredMainDataList;
    private boolean isClick = false;


    public RecyclerviewAdapter(Context context, List<MainData> mainDataList) {
        this.context = context;
        this.mainDataList = mainDataList;
        this.filteredMainDataList = mainDataList;
    }
    @NonNull
    @Override
    public RecyclerviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_row,parent,false);
        return new RecyclerviewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerviewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.userComment.setText(filteredMainDataList.get(position).getUser().getUserComment());
        holder.userImage.setImageResource(filteredMainDataList.get(position).getImageUrl());
        holder.userName.setText(filteredMainDataList.get(position).getUser().getUserName());
        holder.userCircle.setImageResource(filteredMainDataList.get(position).getUser().getUserCircle());
        holder.userLocation.setText(filteredMainDataList.get(position).getUser().getUserLocation());
        ItemAnimation.animateBottomUp(holder.itemView,position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UserDetails.class);
                intent.putExtra("username", filteredMainDataList.get(position).getUser().getUserName());
                intent.putExtra("usercomment", filteredMainDataList.get(position).getUser().getUserComment());
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

        if (filteredMainDataList.get(position).getUser().getUserLocation().contentEquals("")) {
            holder.userLocation.setVisibility(View.INVISIBLE);
        } else {
            holder.userLocation.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public int getItemCount() {
        return filteredMainDataList.size();
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
    public Filter getFilter(){
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String Key = charSequence.toString();
                if(Key.isEmpty()){
                    filteredMainDataList = mainDataList;
                }
                else {
                    List<MainData> lstFiltered = new ArrayList<>();
                    for (MainData row : mainDataList){
                        if(row.getUser().getUserName().toLowerCase().contains(Key.toLowerCase())){
                            lstFiltered.add(row);
                        }
                    }
                    filteredMainDataList = lstFiltered;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredMainDataList;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredMainDataList = (List<MainData>)filterResults.values;
                notifyDataSetChanged();
            }
        };
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


