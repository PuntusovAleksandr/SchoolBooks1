package com.aleksandrp.schoolbooksleeveel1.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aleksandrp.schoolbooksleeveel1.R;
import com.aleksandrp.schoolbooksleeveel1.db.entity.SchoolItem;

import java.util.ArrayList;

/**
 * Created by Aleksandr on 19.11.2015.
 */
public class SchoolsItemsRecyclerAdapter extends
        RecyclerView.Adapter<SchoolsItemsRecyclerAdapter.TimeViewHolder> {

    private ArrayList<SchoolItem> listItems;
    private Context context;


    public SchoolsItemsRecyclerAdapter(ArrayList<SchoolItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    public class TimeViewHolder extends RecyclerView.ViewHolder {

        CardView mCardView;

        TextView mTvName;

        TimeViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.cv_item);
            mTvName = (TextView) itemView.findViewById(R.id.tve_name);
        }
    }

    @Override
    public TimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new TimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TimeViewHolder holder, int position) {
        holder.mTvName.setText(listItems.get(position).getName_item());

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };
}
