package com.example.basicprojectsetup.Utils;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;

import com.example.basicprojectsetup.SharedPreference.Prefs;

public class MyApplication extends Application {

    public static MyApplication instance = null;
    public static Context context;

    public MyApplication() {
        try {
            instance = this;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized static MyApplication getInstance() {
        if (instance == null) {
            instance = new MyApplication();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        instance = this;
        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();
    }
}
