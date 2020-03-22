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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class Tools {

    public static Pattern letter = Pattern.compile("[a-zA-Z]");
    public static Pattern digit = Pattern.compile("[0-9]");
    public static Pattern special = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");

    public static boolean isNetworkAvailable(Activity act) {
        ConnectivityManager connMgr = (ConnectivityManager) act.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }
        return networkInfo != null && networkInfo.isConnected();
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

    public static String convertDateWithoutTime(String date, String flag) {
        String result = "";
        try {
            SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-mm-dd", Locale.getDefault());
            Date parsed = sourceFormat.parse(date);

            SimpleDateFormat destFormat = null;

           /* 1 - dd/mm/yyyy (31/12/2018)
            2 - dd/mm/yyyy (31/12/2018)
            3 - dd/yyyy/mm (31/2018/12)
            4 - dd/yyyy/mm (31/2018/12)
            5 - mm/dd/yyyy (12/31/2018)
            6 - mm/dd/yyyy (12/31/2018)
            7 - mm/yyyy/dd (12/2018/31)
            8 - mm/yyyy/dd (12/2018/31)
            9 - yyyy/dd/mm (2018/31/12)
            10 - yyyy/dd/mm (2018/31/12)
            11 - yyyy/mm/dd (2018/12/31)
            12 - yyyy/mm/dd (2018/12/31)
            13 - dd-mm-yyyy (31-12-2018)*/

            switch (flag) {
                case "1":
                    destFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    break;
                case "2":
                    destFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    break;
                case "3":
                    destFormat = new SimpleDateFormat("dd/yyyy/MM", Locale.getDefault());
                    break;
                case "4":
                    destFormat = new SimpleDateFormat("dd/yyyy/MM", Locale.getDefault());
                    break;
                case "5":
                    destFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
                    break;
                case "6":
                    destFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
                    break;
                case "7":
                    destFormat = new SimpleDateFormat("MM/yyyy/dd", Locale.getDefault());
                    break;
                case "8":
                    destFormat = new SimpleDateFormat("MM/yyyy/dd", Locale.getDefault());
                    break;
                case "9":
                    destFormat = new SimpleDateFormat("yyyy/dd/MM", Locale.getDefault());
                    break;
                case "10":
                    destFormat = new SimpleDateFormat("yyyy/dd/MM", Locale.getDefault());
                    break;
                case "11":
                    destFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
                    break;
                case "12":
                    destFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
                    break;
                case "13":
                    destFormat = new SimpleDateFormat("dd-mm-yyyy", Locale.getDefault());
                    break;
                case "14":
                    destFormat = new SimpleDateFormat("dd MMMM YYYY", Locale.getDefault());
                    break;
                default:
                    destFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    break;
            }
            result = destFormat.format(parsed);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
