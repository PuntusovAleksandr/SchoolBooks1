package com.aleksandrp.schoolbooksleeveel1.frament;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aleksandrp.schoolbooksleeveel1.R;
import com.aleksandrp.schoolbooksleeveel1.StartActivity;
import com.aleksandrp.schoolbooksleeveel1.adapter.GDZRecyclerAdapter;
import com.aleksandrp.schoolbooksleeveel1.db.entity.Book;
import com.aleksandrp.schoolbooksleeveel1.db.functions_db.DBImpl;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GDZFragment extends Fragment {

    private DBImpl db;

    private RecyclerView recyclerView;
    private static GDZFragment fragment;
    public GDZRecyclerAdapter sRecyclerViewAdapter;

    public GDZFragment() {
        // Required empty public constructor
    }

    public static GDZFragment getInstance() {
        if (fragment == null) {
            fragment = new GDZFragment();
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        db = new DBImpl(getActivity());
        View view = inflater.inflate(R.layout.fragment_gdz, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_gdz);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()
                .getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);


        update();

        return view;
    }

    public void update() {
        if (sRecyclerViewAdapter != null) sRecyclerViewAdapter = null;
        sRecyclerViewAdapter = new GDZRecyclerAdapter(getListSchoolItems(), getActivity());
        recyclerView.setAdapter(sRecyclerViewAdapter);
    }

    private ArrayList<Book> getListSchoolItems() {
        if (db == null) db = new DBImpl(getActivity());
        return db.getGDZListByItems(StartActivity.selectItem);
    }

}
