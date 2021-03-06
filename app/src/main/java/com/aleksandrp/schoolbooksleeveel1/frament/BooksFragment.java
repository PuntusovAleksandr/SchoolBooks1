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
import com.aleksandrp.schoolbooksleeveel1.adapter.BookRecyclerAdapter;
import com.aleksandrp.schoolbooksleeveel1.db.entity.Book;
import com.aleksandrp.schoolbooksleeveel1.db.functions_db.DBImpl;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BooksFragment extends Fragment {

    private DBImpl db;
    private RecyclerView recyclerView;
//    public BookRecyclerAdapter sRecyclerViewAdapter;

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
        db = DBImpl.getInstanceDB(getActivity());
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
        BookRecyclerAdapter sRecyclerViewAdapter =
                new BookRecyclerAdapter(getListItemsByBooks(), getActivity());
        recyclerView.setAdapter(sRecyclerViewAdapter);
    }

    private ArrayList<Book> getListItemsByBooks() {
        return db.getBooksListByItems(StartActivity.selectItem);
    }

}
