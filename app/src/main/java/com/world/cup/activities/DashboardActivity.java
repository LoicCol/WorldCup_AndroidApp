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

import com.world.cup.R;
import com.world.cup.adapters.TestAdapter;
import com.world.cup.models.Session;
import com.world.cup.interfaces.OnRecyclerItemClick;

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

    private List<String> test;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        // Init session
        session = new Session(this.getApplicationContext());

         recyclerView.setHasFixedSize(false);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        test = new ArrayList<String>();
        test.add("Bonjour");
        test.add("Test");
        test.add("Aurevoir");

        TestAdapter adapter = new TestAdapter(test, this);
        recyclerView.setAdapter(adapter);


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
        if (session.getUserId() != null && !session.getUserId().isEmpty()) {

        } else {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (session.getUserId() != null && !session.getUserId().isEmpty()) {

        } else {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
    }
}
