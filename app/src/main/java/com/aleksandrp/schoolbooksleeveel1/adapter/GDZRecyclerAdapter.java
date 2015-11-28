package com.aleksandrp.schoolbooksleeveel1.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aleksandrp.schoolbooksleeveel1.R;
import com.aleksandrp.schoolbooksleeveel1.db.entity.Book;

import java.util.ArrayList;

/**
 * Created by Aleksandr on 26.11.2015.
 */
public class GDZRecyclerAdapter extends
        RecyclerView.Adapter<GDZRecyclerAdapter.TimeViewHolder> {

    private ArrayList<Book> listItems;
    private Context context;

    private String link;


    public GDZRecyclerAdapter(ArrayList<Book> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    public class TimeViewHolder extends RecyclerView.ViewHolder {

        CardView mCardView;

        TextView mTvName;
        ImageView mSmallIcon;
        ImageView mIconStatus;


        TimeViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.cv_item);
            mTvName = (TextView) itemView.findViewById(R.id.tve_name);
            mSmallIcon = (ImageView) itemView.findViewById(R.id.iv_small_icon);
            mIconStatus = (ImageView) itemView.findViewById(R.id.iv_icon_status);
        }
    }

    @Override
    public TimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new TimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TimeViewHolder holder, int position) {
        holder.mTvName.setText(listItems.get(position).getNameBook());
        int resSmall = listItems.get(position).getSmallIcon();
        String resStatua = listItems.get(position).getIconStatus();
        if (resSmall != 0) {
            holder.mSmallIcon.setImageResource(resSmall);
        }
        if (resStatua.length() > 1) {
            holder.mIconStatus.setImageResource(R.drawable.pdf_500x500_down);
        }
        link = listItems.get(position).getLinkDownload();
        holder.mCardView.setOnClickListener(listener);
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
            Toast.makeText(context, "Link book :: " + link, Toast.LENGTH_SHORT).show();
        }
    };
}