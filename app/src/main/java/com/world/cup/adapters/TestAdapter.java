package com.world.cup.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.world.cup.R;
import com.world.cup.interfaces.OnRecyclerItemClick;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by loiccol on 30/03/18.
 */

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {
    private List<String> mTeamList;
    private static OnRecyclerItemClick mRecyclerItemClickListener;

    public TestAdapter(List<String> teamList, OnRecyclerItemClick recyclerItemClick) {
        mTeamList = teamList;
        mRecyclerItemClickListener = recyclerItemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.test, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TestAdapter.ViewHolder holder, int position) {
        holder.test.setText(mTeamList.get(position));
    }

    @Override
    public int getItemCount() {
        return mTeamList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener, View.OnLongClickListener{
        @BindView(R.id.test)
        TextView test;



        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mRecyclerItemClickListener.onClick(v, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View v) {
            mRecyclerItemClickListener.onClick(v, getAdapterPosition(), true);
            return true;
        }
    }
}
