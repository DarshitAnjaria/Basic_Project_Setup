package com.example.basicprojectsetup.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;

import java.util.regex.Pattern;

public class Tools {

    public static Pattern letter = Pattern.compile("[a-zA-Z]");
    public static Pattern digit = Pattern.compile("[0-9]");
    public static Pattern special = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");

    public static boolean isNetworkAvailable(Activity act) {
        ConnectivityManager connMgr = (ConnectivityManager) act.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            // fetch data
            return true;
        } else {
            // display error
            return false;
        }
    }

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return model;
        } else {
            return manufacturer + " " + model;
        }
    }

    public static String getAndroidVersionName(Context ctx) {
        try {
            PackageManager manager = ctx.getPackageManager();
            PackageInfo info = manager.getPackageInfo(ctx.getPackageName(), 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return e.getMessage();
        }
    }

    public static int getVersionCode(Context ctx) {
        try {
            PackageManager manager = ctx.getPackageManager();
            PackageInfo info = manager.getPackageInfo(ctx.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return -1;
        }
    }

    public static int getScreenWidth(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeight(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static int dpToPx(int dp, Activity activity) {
        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public static int pxToDp(int px, Activity activity) {
        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public static boolean isStringNull(String value) {
        boolean isStringNull = true;
        if (value != null && !value.equals("") && !value.equals("null")) {
            isStringNull = false;
        }
        return isStringNull;
    }
}
