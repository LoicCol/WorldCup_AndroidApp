package com.world.cup.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import com.world.cup.entities.AccessToken;
import com.world.cup.responses.ForecastResponse;
import com.world.cup.responses.GamesResponse;
import com.world.cup.responses.PostResponse;
import com.world.cup.responses.UserResponse;

public interface ApiService {

    @POST("register")
    @FormUrlEncoded
    Call<AccessToken> register(@Field("name") String name, @Field("pseudo") String pseudo, @Field("email") String email, @Field("password") String password,
                               @Field("password_confirmation") String password_confirmation);

    @POST("login")
    @FormUrlEncoded
    Call<AccessToken> login(@Field("username") String username, @Field("password") String password);

    @POST("refresh")
    @FormUrlEncoded
    Call<AccessToken> refresh(@Field("refresh_token") String refreshToken);

    @GET("posts")
    Call<PostResponse> posts();

    @GET("user")
    Call<UserResponse> user();

    @GET("games")
    Call<GamesResponse> games();

    @GET("games")
    Call<ForecastResponse> forecasts();

    @POST("forecasts")
    @FormUrlEncoded
    Call<ResponseBody> forecast(@Field("game_id") String gameId,
                                @Field("score_team1") String score1,
                                @Field("score_team1") String score2,
                                @Field("team1_id") String team1_id,
                                @Field("team2_id") String team2_id);
}
