package com.world.cup.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.world.cup.R;
import com.world.cup.network.ApiService;
import com.world.cup.network.RetrofitBuilder;
import com.world.cup.utils.TokenManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class BetFragment extends DialogFragment {

    private static final String TAG = "BetFragment";
    @BindView(R.id.team1_score)
    EditText score1;
    @BindView(R.id.team2_score)
    EditText score2;
    @BindView(R.id.titreTeam1)
    TextView titreTeam1;
    @BindView(R.id.titreTeam2)
    TextView titreTeam2;
    @BindView(R.id.betButton)
    Button betButton;
    Unbinder unbinder;

    private OnFragmentInteractionListener mListener;

    private Integer gameId;
    private Integer team1Id;
    private Integer team2Id;
    private TokenManager tokenManager;
    private ApiService service;
    private Call<ResponseBody> callForecast;

    public BetFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = View.inflate(getContext(), R.layout.dialog_bet, null);
        unbinder = ButterKnife.bind(this, view);

        Bundle args = getArguments();
        gameId = args.getInt("gameId", 0);
        team1Id = args.getInt("team1_id");
        team2Id = args.getInt("team2_id");
        titreTeam1.setText(args.getString("team1"));
        titreTeam2.setText(args.getString("team2"));
        score1.setText(String.valueOf(args.getInt("score1", 0)));
        score2.setText(String.valueOf(args.getInt("score2", 0)));

        tokenManager = TokenManager.getInstance(PreferenceManager.getDefaultSharedPreferences(getActivity()));
        service = RetrofitBuilder.createServiceWithAuth(ApiService.class, tokenManager);

        score1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_RIGHT = 2;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    int textWidth = score1.getMeasuredWidth() - (score1.getCompoundDrawables()[DRAWABLE_LEFT].getBounds().width() + score1.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width());
                    if (event.getX() >= (textWidth + score1.getCompoundDrawables()[DRAWABLE_LEFT].getBounds().width())) {
                        int score = Integer.parseInt(score1.getText().toString());
                        score++;
                        score1.setText(String.valueOf(score));
                        return true;
                    }
                    if (event.getX() <= (score1.getCompoundDrawables()[DRAWABLE_LEFT].getBounds().width())) {
                        int score = Integer.parseInt(score1.getText().toString());
                        if (score > 0){
                            score--;
                        }
                        score1.setText(String.valueOf(score));
                        return true;
                    }
                }
                return false;
            }
        });

        score2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_RIGHT = 2;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    int textWidth = score2.getMeasuredWidth() - (score2.getCompoundDrawables()[DRAWABLE_LEFT].getBounds().width() + score2.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width());
                    if (event.getX() >= (textWidth + score2.getCompoundDrawables()[DRAWABLE_LEFT].getBounds().width())) {
                        int score = Integer.parseInt(score2.getText().toString());
                        score++;
                        score2.setText(String.valueOf(score));
                        return true;
                    }
                    if (event.getX() <= (score2.getCompoundDrawables()[DRAWABLE_LEFT].getBounds().width())) {
                        int score = Integer.parseInt(score2.getText().toString());
                        if (score > 0) {
                            score--;
                        }
                        score2.setText(String.valueOf(score));
                        return true;
                    }
                }
                return false;
            }
        });

        betButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendForecast();
                dismiss();
            }
        });

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void sendForecast() {
        callForecast = service.forecast(gameId.toString(), score1.getText().toString(), score2.getText().toString(), team1Id.toString(), team2Id.toString());
        callForecast.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.v(TAG, "onResponse : " + response);
                if (response.isSuccessful()) {
                    Log.v(TAG, "Forecast Successful");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.w(TAG, "onFailure: " + t.getMessage());
            }
        });
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
