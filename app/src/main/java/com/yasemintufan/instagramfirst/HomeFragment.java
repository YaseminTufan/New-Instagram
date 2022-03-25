package com.yasemintufan.instagramfirst;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yasemintufan.instagramfirst.adapter.RecyclerviewAdapter;
import com.yasemintufan.instagramfirst.model.MainData;
import com.yasemintufan.instagramfirst.model.User;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    RecyclerView userRecycler;
    RecyclerviewAdapter recyclerviewAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        userRecycler = view.findViewById(R.id.homeRecyclerView);
        createMainData();
        return view;
    }

    private void setUserRecycler(List<MainData> mainDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        userRecycler.setLayoutManager(layoutManager);
        recyclerviewAdapter = new RecyclerviewAdapter(getContext(), mainDataList);
        userRecycler.setAdapter(recyclerviewAdapter);
    }

    public void createMainData() {
        List<MainData> mainDataList = new ArrayList<>();

        mainDataList.add(new MainData((R.drawable.manzara1), new User("Michael Isabella", "Night", R.drawable.ves1, "Ankara")));
        mainDataList.add(new MainData((R.drawable.manzara2), new User("Olivia Alfie", "Play", R.drawable.ves2, "New York")));
        mainDataList.add(new MainData((R.drawable.manzara3), new User("Daniel Hannah", "My dreams house", R.drawable.ves3, "Turkey İstanbul")));
        mainDataList.add(new MainData((R.drawable.manzara4), new User("William Madison", "The sea is wonderful", R.drawable.ves4, "")));
        mainDataList.add(new MainData((R.drawable.manzara5), new User("George Ethan", "Maldives", R.drawable.ves5, "Maldives")));
        mainDataList.add(new MainData((R.drawable.manzara6), new User("Daniel Abigail", "50 shades of green", R.drawable.ves6, "")));
        mainDataList.add(new MainData((R.drawable.manzara8), new User("Jacob Hily", "Holiday", R.drawable.ves7, "Roma Italy")));
        mainDataList.add(new MainData((R.drawable.manzara1), new User("Michael Isabella", "Night", R.drawable.ves1, "")));
        mainDataList.add(new MainData((R.drawable.manzara2), new User("Olivia Alfie", "Amazing", R.drawable.ves2, "")));
        mainDataList.add(new MainData((R.drawable.manzara3), new User("Daniel Hannah", "My dreams house", R.drawable.ves3, "Atatürk Ormanı")));
        mainDataList.add(new MainData((R.drawable.manzara4), new User("William Madison", "Holiday", R.drawable.ves4, "")));
        mainDataList.add(new MainData((R.drawable.manzara5), new User("George Ethan", "The sea is wonderful", R.drawable.ves5, "Antalya Lara")));
        mainDataList.add(new MainData((R.drawable.manzara6), new User("Daniel Abigail", "Amazing", R.drawable.ves6, "")));

        setUserRecycler(mainDataList);
    }
}