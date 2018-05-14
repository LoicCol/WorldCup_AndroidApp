package com.world.cup.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.world.cup.R;
import com.world.cup.interfaces.OnRecyclerItemClick;
import com.world.cup.entities.Game;

import java.lang.reflect.Field;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by loiccol on 30/03/18.
 */

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {
    private List<Game> mTeamList;
    private static OnRecyclerItemClick mRecyclerItemClickListener;
    private Context context;

    public GameAdapter(List<Game> teamList, OnRecyclerItemClick recyclerItemClick) {
        mTeamList = teamList;
        mRecyclerItemClickListener = recyclerItemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.match_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GameAdapter.ViewHolder holder, int position) {
        holder.team1.setText(mTeamList.get(position).getHomeTeam().getName());
        holder.team2.setText(mTeamList.get(position).getAwayTeam().getName());
        String flagTeam1 = "ic_list_country_" + mTeamList.get(position).getHomeTeam().getLogoCode();
        int idTeam1 = context.getResources().getIdentifier(flagTeam1, "drawable", context.getPackageName());
        holder.team1Img.setImageResource(idTeam1);
        String flagTeam2 = "ic_list_country_" + mTeamList.get(position).getAwayTeam().getLogoCode();
        int idTeam2 = context.getResources().getIdentifier(flagTeam2, "drawable", context.getPackageName());
        holder.team2Img.setImageResource(idTeam2);
        holder.date.setText(mTeamList.get(position).getDateTime().toString());
    }

    @Override
    public int getItemCount() {
        return mTeamList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.team1)
        TextView team1;
        @BindView(R.id.team2)
        TextView team2;
        @BindView(R.id.team1_img)
        ImageView team1Img;
        @BindView(R.id.team2_img)
        ImageView team2Img;
        TextView score;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.bet)
        Button betButton;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            betButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mRecyclerItemClickListener.onClick(v, getAdapterPosition(), false);
        }
    }
}
