package com.world.cup.classes;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by loiccol on 02/04/18.
 */

public class Session {

    private SharedPreferences prefs;

    public Session(Context cntx) {
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setUserId(String userId) {
        prefs.edit()
                .putString("userId", userId)
                .commit();
    }

    public String getUserId() {
        try {
            String userId = prefs.getString("userId","");
            return userId;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }

}
