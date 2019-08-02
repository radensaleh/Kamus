package com.example.rdsaleh.kamus.Prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.rdsaleh.kamus.R;

public class AppPreference {

    private SharedPreferences mPrefs;
    private Context mContext;

    public AppPreference(Context mContext){
        mPrefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        this.mContext = mContext;
    }

    public void setFirstRun(Boolean input){
        SharedPreferences.Editor editor = mPrefs.edit();
        String key = mContext.getResources().getString(R.string.app_first_run);
        editor.putBoolean(key, input);
        editor.apply();
    }

    public Boolean getFirstRun(){
        String key = mContext.getResources().getString(R.string.app_first_run);
        return mPrefs.getBoolean(key, true);
    }

}
