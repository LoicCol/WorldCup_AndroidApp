package com.world.cup.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.world.cup.R;
import com.world.cup.adapters.TestAdapter;
import com.world.cup.interfaces.OnRecyclerItemClick;
import com.world.cup.models.Match;
import com.world.cup.models.Team;
import com.world.cup.utils.TokenManager;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    private List<Match> test;
    private TokenManager tokenManager;

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

        test = new ArrayList<Match>();
        for (int i = 0; i < 100; i++) {
            test.add(new Match(new Date(900270000), "Poule A", new Team("France"), new Team("Brasil"), 0, 0));
        }

        TestAdapter adapter = new TestAdapter(test, this);
        recyclerView.setAdapter(adapter);

        disconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tokenManager.deleteToken();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        /* tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition()) {
                    case 0:
                  ....
                }
            } */
    }

    @Override
    public void onClick(View view, int position, boolean isLongClick) {
        String stringToDisplay = "";
        if (isLongClick) {
            stringToDisplay = "Long clique sur " + test.get(position);
        } else {
            stringToDisplay = "Petit clique sur " + test.get(position);
        }

        Snackbar snackbar = Snackbar
                .make(recyclerView, stringToDisplay, Snackbar.LENGTH_LONG);
        snackbar.show();
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
}
