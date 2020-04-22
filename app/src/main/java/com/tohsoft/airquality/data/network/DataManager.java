package com.tohsoft.airquality.data.network;


import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.blankj.utilcode.util.NetworkUtils;
import com.tohsoft.airquality.BuildConfig;
import com.tohsoft.airquality.data.local.preference.PreferencesHelper;
import com.tohsoft.airquality.data.models.MoreApps;
import com.tohsoft.airquality.data.models.RankingCountry;
import com.tohsoft.airquality.data.models.RoundMap;
import com.tohsoft.airquality.data.models.User;
import com.tohsoft.airquality.forecast.AqiSettings;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import io.reactivex.Observable;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.content.Context.WINDOW_SERVICE;
import static com.tohsoft.airquality.forecast.Util.getUrlEncodedLang;

/**
 * Created by Phong on 11/9/2016.
 */

public class DataManager {
    private final static String tokenwaqi = "4ef577fe7f69a1706f1261ac805f54406dee219d";
    private final static String shortApiData = "v10.json";
    private final static String fullApiData = "v11.json";
    private RemoteApiService mRemoteApiService;
    private RemoteAQIApiService mRemoteAQIApiService;
    private RemoteRankingApiService mRemoteRankingApiService;
    private RemoteFullDataApiService mRemoteFullDataApiService;
    private PreferencesHelper mPreferencesHelper;

    public DataManager(RemoteApiService remoteApiService, RemoteAQIApiService mRemoteAQIApiService, RemoteRankingApiService mRemoteRankingApiService, RemoteFullDataApiService mRemoteFullDataApiService, PreferencesHelper preferencesHelper) {
        this.mRemoteApiService = remoteApiService;
        this.mRemoteAQIApiService = mRemoteAQIApiService;
        this.mRemoteRankingApiService = mRemoteRankingApiService;
        this.mRemoteFullDataApiService = mRemoteFullDataApiService;
        this.mPreferencesHelper = preferencesHelper;
    }

    public Observable<RankingCountry> getListTopRanking() {
        return mRemoteRankingApiService.getRankingAllCountry();
    }

    public Observable<RankingCountry> getRankingCityInCountry(String idCountry) {
        return mRemoteRankingApiService.getRankingCountry(idCountry);
    }

    public Observable<User> login(String email, String password, String android_push_key) {
        return mRemoteApiService.login(email, password, android_push_key);
    }

    /**
     * @param location location of user null for testing
     * @return
     */
    public Observable<ResponseBody> getFeedByLocation(Location location) {
        String geo = location == null ? "geo:20.9968;105.8408" : "geo:" + location.getLatitude() + ";" + location.getLongitude();
        return getFeedByCity(geo);
    }

    public Observable<ResponseBody> getFeedByCity(String location) {
        return mRemoteAQIApiService.getFeed(location, tokenwaqi);
    }

    public Observable<ResponseBody> getFeedByIP() {
        return mRemoteAQIApiService.getFeed("here", tokenwaqi);
    }

    public Observable<RoundMap> getMapRound(Location... location) {
        StringBuilder s = new StringBuilder();
        for (Location temp : location) {
            if (s.length() > 0) {
                s.append(",");
            }
            s.append(temp.getLatitude()).append(",").append(temp.getLongitude());
        }
        return mRemoteAQIApiService.getMapBound( tokenwaqi,s.toString());
    }

    @SuppressLint("HardwareIds")
    public Observable<ResponseBody> getForecastData(Context ctx, String tokenKeyCity, String cityId) {
        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager) ctx.getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getMetrics(metrics);
        String metric = metrics.widthPixels + "," + metrics.heightPixels + "," + metrics.density;
        try {
            if (((ConnectivityManager) ctx.getSystemService(CONNECTIVITY_SERVICE)).getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()) {
                metric = metric + "&wifi";
            }
        } catch (Exception ignored) {
        }
        try {
            metric = metric + Settings.Secure.getString(ctx.getContentResolver(), Settings.Secure.ANDROID_ID);
        } catch (Exception ignored) {
        }
        //appv :version app and appn  version name crawl api dont change it
        return mRemoteFullDataApiService.fullData(tokenKeyCity,
                fullApiData,
                cityId,
                getUrlEncodedLang(),
                AqiSettings.CITY,
                "132",
                "3.5",
                TimeZone.getDefault().getRawOffset() + "",
                metric);
    }

    public Observable<MoreApps> getMoreApps() {
        Map<String, String> params = new HashMap<>();
        params.put("app_id", BuildConfig.APPLICATION_ID);
        return mRemoteApiService.moreApps(params);
    }
}
