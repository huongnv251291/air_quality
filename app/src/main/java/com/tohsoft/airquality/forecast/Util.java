package com.tohsoft.airquality.forecast;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.TimeZone;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.content.Context.WINDOW_SERVICE;

public class Util {
    public static boolean useEnglish() {
        return !useNative();
    }

    public static boolean useNative() {
        String l = getLang();
        return l.compareTo("jp") == 0 || l.compareTo("cn") == 0 || l.compareTo("kr") == 0 || l.compareTo("hk") == 0;
    }

    public static String space() {
        return useNative() ? "" : " ";
    }

    public static String getShortLang() {
        String l = getLang();
        if (l.contains("_")) {
            return l.substring(0, l.indexOf("_"));
        }
        return l;
    }

    public static String getLang() {
        String l = Locale.getDefault().getLanguage();
        if (l.compareTo("ja") == 0 || l.compareTo("ja_JP") == 0) {
            l = "jp";
        }
        if (l.compareTo("ko") == 0 || l.compareTo("ko_KR") == 0) {
            l = "kr";
        }
        if (l.compareTo("zh") == 0 || l.compareTo("zh_CN") == 0) {
            l = "cn";
        }
        if (l.compareTo("zh") == 0 || l.compareTo("zh_CN") == 0) {
            l = "cn";
        }
        if (l.compareTo("zh_TW") == 0) {
            return "hk";
        }
        return l;
    }

    public static String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "utf-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public static String getUrlEncodedLang() {
        try {
            return URLEncoder.encode(Locale.getDefault().getLanguage(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public static String getUrlExtraInfoWithDevId(Context ctx) {
        String u = getDataUrlExtraInfo(ctx);
        try {
            return u + "&devid=" + Settings.Secure.getString(ctx.getContentResolver(), Settings.Secure.ANDROID_ID);
        } catch (Exception e) {
            return u;
        }
    }

    public static String getDataUrlExtraInfo(Context ctx) {
        String u = "&lang=" + getUrlEncodedLang() + "&package=" + AqiSettings.CITY;
        try {
            PackageInfo v = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
//            u = (u + "&appv=" + v.versionCode + "&appn=" + v.versionName) + "&tz=" + TimeZone.getDefault().getRawOffset();
            u = (u + "&appv=" + 132 + "&appn=" + "3.5") + "&tz=" + TimeZone.getDefault().getRawOffset();
        } catch (Exception e) {
        }
        try {
            DisplayMetrics metrics = new DisplayMetrics();
            ((WindowManager) ctx.getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getMetrics(metrics);
            u = u + "&metrics=" + metrics.widthPixels + "," + metrics.heightPixels + "," + metrics.density;
        } catch (Exception e2) {
        }
        try {
            if (((ConnectivityManager) ctx.getSystemService(CONNECTIVITY_SERVICE)).getNetworkInfo(1).isConnected()) {
                return u + "&wifi";
            }
            return u;
        } catch (Exception e3) {
            return u;
        }
    }

    public static String getUtmUrlExtraInfo(Context ctx) {
        try {
            PackageInfo v = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
            return ("" + "&utm_source=app-v" + v.versionCode + "-" + v.versionName) + "&utm_content=" + AqiSettings.CITY;
        } catch (Exception e) {
            return "";
        }
    }

    public static String getRawResource(Context ctx, int id) {
        try {
            InputStream is = ctx.getResources().openRawResource(id);
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            byte[] readBuffer = new byte[16384];
            while (true) {
                int read = is.read(readBuffer, 0, readBuffer.length);
                if (read == -1) {
                    return new String(bout.toByteArray());
                }
                bout.write(readBuffer, 0, read);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getIntentInfo(Intent i) {
        String s = i.toString() + " Action:" + i.getAction();
        Bundle extras = i.getExtras();
        String se = "";
        if (extras != null) {
            for (String e : extras.keySet()) {
                String v = "-";
                try {
                    v = extras.get(e).toString();
                } catch (Exception e2) {
                }
                se = se + e + ":" + v + ", ";
            }
        }
        if (se.length() != 0) {
            return s + " [" + se + "]";
        }
        return s;
    }

    public static boolean isMsPhone() {
        return Build.MANUFACTURER.compareToIgnoreCase("nokia") == 0 || Build.MANUFACTURER.compareToIgnoreCase("microsfot") == 0 || Build.MANUFACTURER.compareToIgnoreCase("shield") == 0;
    }

    public static int getGeoAccuracy(Context ctx) {
        if (ctx.getPackageManager().checkPermission("android.permission.ACCESS_FINE_LOCATION", ctx.getPackageName()) != PackageManager.PERMISSION_GRANTED) {
            return 1;
        }
        return 2;
    }
}