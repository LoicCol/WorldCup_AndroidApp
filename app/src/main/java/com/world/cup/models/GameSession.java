package com.world.cup.models;

import android.util.Log;

import com.world.cup.network.ApiService;
import com.world.cup.responses.GamesResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by Admin on 20/04/2018.
 */

public class GameSession {
    Response<GamesResponse> resp;

    public GameSession(){
    }

    public Response<GamesResponse> getGamesResponse(Call<GamesResponse> callGame, ApiService service){
        resp = null;
        callGame = service.games();
        callGame.enqueue(new Callback<GamesResponse>() {
            @Override
            public void onResponse(Call<GamesResponse> call, Response<GamesResponse> response) {
                Log.w(TAG, "onResponse: " + response );

                if(response.isSuccessful()){
                    resp=response;
                }
            }

            @Override
            public void onFailure(Call<GamesResponse> call, Throwable t) {
                Log.w(TAG, "onFailure: " + t.getMessage() );
            }
        });
        return resp;
    }
}
