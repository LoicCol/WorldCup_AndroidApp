package com.world.cup.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.world.cup.R;
import com.world.cup.adapters.TestAdapter;
import com.world.cup.classes.Session;
import com.world.cup.interfaces.OnRecyclerItemClick;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardActivity extends AppCompatActivity implements OnRecyclerItemClick{

    @BindView(R.id.my_recycler_view)
    RecyclerView myRecyclerView;
    private List<String> test;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        // Init session
        session = new Session(this.getApplicationContext());

        myRecyclerView.setHasFixedSize(false);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(layoutManager);

        test = new ArrayList<String>();
        test.add("Bonjour");
        test.add("Test");
        test.add("Aurevoir");

        TestAdapter adapter = new TestAdapter(test, this);
        myRecyclerView.setAdapter(adapter);
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
                .make(myRecyclerView, stringToDisplay, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(session.getUserId() != null && !session.getUserId().isEmpty()) {

        } else {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if(session.getUserId() != null && !session.getUserId().isEmpty()) {

        } else {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
    }
}
