package com.world.cup.models;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.world.cup.activities.LoginActivity;
import com.world.cup.activities.PostActivity;
import com.world.cup.entities.User;
import com.world.cup.network.ApiService;
import com.world.cup.responses.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by Admin on 20/04/2018.
 */

public class UserSession {

    Response<UserResponse> resp;

    public UserSession(){
    }

    public Response<UserResponse> getUserResponse(Call<UserResponse> callUser, ApiService service){
        callUser = service.user();
        callUser.enqueue(new Callback<UserResponse>() {
            Response<UserResponse> response;

            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                Log.w(TAG, "onResponse: " + response );

                if(response.isSuccessful()){
                    System.out.println("response = " + response.toString());
                    resp = response;
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.w(TAG, "onFailure: " + t.getMessage() );
            }

            Response<UserResponse> getResponse(){
                return response;
            }
        });
        return resp;
    }
}