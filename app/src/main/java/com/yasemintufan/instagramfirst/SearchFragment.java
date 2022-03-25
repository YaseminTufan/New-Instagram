package com.yasemintufan.instagramfirst;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import com.yasemintufan.instagramfirst.adapter.RecyclerviewAdapter;
import com.yasemintufan.instagramfirst.adapter.RecyclerviewImageAdapter;
import com.yasemintufan.instagramfirst.model.ImageData;
import com.yasemintufan.instagramfirst.model.MainData;
import com.yasemintufan.instagramfirst.model.User;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    RecyclerviewAdapter recyclerviewAdapter;
    EditText searchView;
    CharSequence search = "";
    RecyclerView searchRecyclerView;
    RecyclerviewImageAdapter recyclerviewImageAdapter;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        searchView = view.findViewById(R.id.search_bar);
        searchViewTextWatcher(searchView);
        searchRecyclerView = view.findViewById(R.id.RecyclerSearchFragment);
        createImageData();
        return view;
    }
    private void setSearchRecyclerView (List<ImageData>imageDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        searchRecyclerView.setLayoutManager(layoutManager);
        recyclerviewImageAdapter = new RecyclerviewImageAdapter(getContext(),imageDataList);
        searchRecyclerView.setAdapter(recyclerviewImageAdapter);
    }
    public void createImageData(){
        List<ImageData>imageDataList = new ArrayList<>();
        imageDataList.add(new ImageData(R.drawable.tatil));
        imageDataList.add(new ImageData(R.drawable.tatil1));
        imageDataList.add(new ImageData(R.drawable.tatil2));
        imageDataList.add(new ImageData(R.drawable.tatil3));
        imageDataList.add(new ImageData(R.drawable.tatil4));
        imageDataList.add(new ImageData(R.drawable.tatil5));
        imageDataList.add(new ImageData(R.drawable.tatil6));
        imageDataList.add(new ImageData(R.drawable.tatil7));
        imageDataList.add(new ImageData(R.drawable.tatil8));
        imageDataList.add(new ImageData(R.drawable.tatil9));
        imageDataList.add(new ImageData(R.drawable.tatil));
        imageDataList.add(new ImageData(R.drawable.tatil1));
        imageDataList.add(new ImageData(R.drawable.tatil2));
        imageDataList.add(new ImageData(R.drawable.tatil3));
        imageDataList.add(new ImageData(R.drawable.tatil4));
        imageDataList.add(new ImageData(R.drawable.tatil5));
        imageDataList.add(new ImageData(R.drawable.tatil6));
        imageDataList.add(new ImageData(R.drawable.tatil7));
        imageDataList.add(new ImageData(R.drawable.tatil8));
        imageDataList.add(new ImageData(R.drawable.tatil9));
        imageDataList.add(new ImageData(R.drawable.tatil));

        setSearchRecyclerView(imageDataList);
        searchRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));

    }


    private void searchViewTextWatcher(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                recyclerviewAdapter.getFilter().filter(charSequence);
                search = charSequence;
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
}
