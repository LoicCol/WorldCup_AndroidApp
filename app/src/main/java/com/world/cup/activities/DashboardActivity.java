package com.world.cup.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import com.world.cup.R;
import com.world.cup.adapters.GameAdapter;
import com.world.cup.interfaces.OnRecyclerItemClick;
import com.world.cup.network.ApiService;
import com.world.cup.entities.Game;
import com.world.cup.network.RetrofitBuilder;
import com.world.cup.responses.GamesResponse;
import com.world.cup.utils.TokenManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.ConstraintSet.WRAP_CONTENT;

public class DashboardActivity extends AppCompatActivity implements OnRecyclerItemClick {

    private static final String TAG = "DashboardActivity";
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.disconnect)
    ImageButton disconnect;

    private List<Game> games;
    private TokenManager tokenManager;
    private ApiService service;
    private Call<GamesResponse> callGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        // Init session
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));
        toolbar.setTitle(R.string.app_name);
        toolbar.setSubtitle("Nom du joueur");
        toolbar.setNavigationIcon(R.drawable.ic_launcher_foreground);

        recyclerView.setHasFixedSize(false);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        games = new ArrayList<>();

        service = RetrofitBuilder.createServiceWithAuth(ApiService.class, tokenManager);

        getGamesResponse();

        disconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tokenManager.deleteToken();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        Log.v(TAG, "Case tab 0");
                    case 1:
                        getGamesResponse();
                    case 2:
                        Log.v(TAG, "Case tab 2");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        Log.v(TAG, "Case tab 0");
                    case 1:
                        getGamesResponse();
                    case 2:
                        Log.v(TAG, "Case tab 2");
                }
            }
        });
    }

    @Override
    public void onClick(View view, int position, boolean isLongClick) {
        String stringToDisplay = "";
        Log.v(TAG, games.get(position).toString());

        Game game = games.get(position);

        FragmentManager fragmentManager = getSupportFragmentManager();
        BetFragment frag = new BetFragment();
        Bundle args = new Bundle();

        args.putInt("gameId", game.getId());
        args.putInt("team1_id", game.getHomeTeam().getId());
        args.putInt("team2_id", game.getAwayTeam().getId());
        args.putInt("score1", 0);
        args.putInt("score2", 0);
        args.putString("team1", game.getHomeTeam().getName());
        args.putString("team2", game.getAwayTeam().getName());
        frag.setArguments(args);

        frag.show(fragmentManager, "BetDialog");

        // Window window = frag.getDialog().getWindow().setLayout(WRAP);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (tokenManager.getToken().getAccessToken() != null) {

        } else {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (tokenManager.getToken().getAccessToken() != null) {

        } else {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
    }

    static class ViewHolder {
        @BindView(R.id.recycler_view)
        RecyclerView recyclerView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public void getGamesResponse(){
        callGame = service.games();
        callGame.enqueue(new Callback<GamesResponse>() {
            @Override
            public void onResponse(Call<GamesResponse> call, Response<GamesResponse> response) {
                Log.v(TAG, "onResponse : " + response);
                if(response.isSuccessful()){
                    setGameAdapater(response.body().getData());
                    games = response.body().getData();
                }
            }

            @Override
            public void onFailure(Call<GamesResponse> call, Throwable t) {
                Log.w(TAG, "onFailure: " + t.getMessage() );
            }
        });
    }

    protected void setGameAdapater(List<Game> games) {
        GameAdapter adapter = new GameAdapter(games, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(callGame != null){
            callGame.cancel();
            callGame = null;
        }
    }
}
