package com.world.cup.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.world.cup.R;
import com.world.cup.entities.User;
import com.world.cup.models.GameSession;
import com.world.cup.models.UserSession;
import com.world.cup.responses.GamesResponse;
import com.world.cup.responses.UserResponse;
import com.world.cup.network.ApiService;
import com.world.cup.network.RetrofitBuilder;
import com.world.cup.utils.TokenManager;

public class PostActivity extends AppCompatActivity {

    private static final String TAG = "PostActivity";
    
    @BindView(R.id.post_title)
    TextView title;

    ApiService service;
    TokenManager tokenManager;
    Call<UserResponse> callUser;
    Call<GamesResponse> callGame;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        ButterKnife.bind(this);
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));

        if(tokenManager.getToken() == null){
            startActivity(new Intent(PostActivity.this, LoginActivity.class));
            finish();
        }

        service = RetrofitBuilder.createServiceWithAuth(ApiService.class, tokenManager);
    }

    @OnClick(R.id.btn_posts)
    void getPosts(){
        UserSession usersession = new UserSession();
        Response<UserResponse> userResponse = usersession.getUserResponse(callUser, service);
        if (userResponse != null){
            title.setText(userResponse.body().getData().getName());
        }else {
            startActivity(new Intent(PostActivity.this, LoginActivity.class));
            finish();
        }

        /*GameSession gamesession = new GameSession();
        Response<GamesResponse> gamesResponse = gamesession.getGamesResponse(callGame, service);
        if (gamesResponse != null){
            title.setText(gamesResponse.body().getData().get(0).getGroup());
        }else {
            startActivity(new Intent(PostActivity.this, LoginActivity.class));
            finish();
        }*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(callUser != null){
            callUser.cancel();
            callUser = null;
        }
        if(callGame != null){
            callGame.cancel();
            callGame = null;
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
