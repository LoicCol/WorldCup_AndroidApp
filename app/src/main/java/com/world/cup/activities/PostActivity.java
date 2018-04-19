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
import com.world.cup.classes.PostResponse;
import com.world.cup.classes.UserResponse;
import com.world.cup.network.ApiService;
import com.world.cup.network.RetrofitBuilder;
import com.world.cup.utils.TokenManager;

public class PostActivity extends AppCompatActivity {

    private static final String TAG = "PostActivity";
    
    @BindView(R.id.post_title)
    TextView title;

    ApiService service;
    TokenManager tokenManager;
    Call<UserResponse> call;


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

        call = service.user();
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                Log.w(TAG, "onResponse: " + response );

                if(response.isSuccessful()){
                    title.setText(response.body().getData().getName());
                }else {
                    tokenManager.deleteToken();
                    startActivity(new Intent(PostActivity.this, LoginActivity.class));
                    finish();

                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.w(TAG, "onFailure: " + t.getMessage() );
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(call != null){
            call.cancel();
            call = null;
        }
    }
}
