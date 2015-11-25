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
import com.aleksandrp.schoolbooksleeveel1.adapter.SchoolsItemsRecyclerAdapter;
import com.aleksandrp.schoolbooksleeveel1.db.entity.SchoolItem;
import com.aleksandrp.schoolbooksleeveel1.db.functions_db.DBImpl;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BooksFragment extends Fragment {

    private DBImpl db;
    private RecyclerView recyclerView;
    public SchoolsItemsRecyclerAdapter sRecyclerViewAdapter;

    private static BooksFragment fragment;


    public BooksFragment() {
        // Required empty public constructor
    }

    public static BooksFragment getInstance() {
        if (fragment == null) {
            fragment = new BooksFragment();
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        db = new DBImpl(getActivity());
        View view = inflater.inflate(R.layout.fragment_books, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_books);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().
                getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        updateList();

        return view;
    }

    public void updateList() {
        if (sRecyclerViewAdapter != null) sRecyclerViewAdapter = null;
        sRecyclerViewAdapter = new SchoolsItemsRecyclerAdapter(getListItemsByBooks(), getActivity());
        recyclerView.setAdapter(sRecyclerViewAdapter);
    }

    private ArrayList<SchoolItem> getListItemsByBooks() {
        if (db == null) db = new DBImpl(getActivity());
        return db.getSchoolItems(StartActivity.selectLevel);
    }

}
