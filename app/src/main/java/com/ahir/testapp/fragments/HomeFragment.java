package com.ahir.testapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahir.testapp.R;
import com.ahir.testapp.adapter.ItemAdapter;
import com.ahir.testapp.data.ItemListAsyncResponse;
import com.ahir.testapp.data.Repository;
import com.ahir.testapp.model.ListItem;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    ArrayList<ListItem> items = new ArrayList<>();
    private ItemAdapter itemAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView;

        recyclerView = view.findViewById(R.id.recycler_view);
        setUpItem();

        itemAdapter = new ItemAdapter(getContext(), items);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

    }

    private void setUpItem() {
//      Getting it from the repository
   items =new Repository().getListItem(new ItemListAsyncResponse() {
            @Override
            public void processFinished(ArrayList<ListItem> listItemArrayList) {

                Log.d("MAIN", listItemArrayList.get(0).toString());

                itemAdapter.setItems(listItemArrayList);

            }
       });

    }
}