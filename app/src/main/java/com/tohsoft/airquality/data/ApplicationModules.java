package com.tohsoft.airquality.data;

import android.annotation.SuppressLint;
import android.content.Context;

import com.tohsoft.airquality.data.local.preference.PreferencesHelper;
import com.tohsoft.airquality.data.network.DataManager;
import com.tohsoft.airquality.data.network.RemoteAQIApiService;
import com.tohsoft.airquality.data.network.RemoteApiService;
import com.tohsoft.airquality.data.network.RemoteFullDataApiService;
import com.tohsoft.airquality.data.network.RemoteRankingApiService;

/**
 * Created by Phong on 3/1/2017.
 */

public class ApplicationModules {
    @SuppressLint("StaticFieldLeak")
    private static ApplicationModules sApplicationModules;
    private Context mContext;
    private PreferencesHelper mPreferencesHelper;
    private DataManager mDataManager;

    public static ApplicationModules getInstant() {
        if (sApplicationModules == null) {
            sApplicationModules = new ApplicationModules();
        }
        return sApplicationModules;
    }

    public Context getContext() {
        return mContext;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    /*
     *Initialize modules for app
     */
    public void initModules(Context context) {
        mContext = context;
        mPreferencesHelper = new PreferencesHelper(context);
        mDataManager = new DataManager(
                RemoteApiService.Creator.newRetrofitInstance().create(RemoteApiService.class),
                RemoteAQIApiService.Creator.newRetrofitInstance().create(RemoteAQIApiService.class),
                RemoteRankingApiService.Creator.newRetrofitInstance().create(RemoteRankingApiService.class),
                RemoteFullDataApiService.Creator.newRetrofitInstance().create(RemoteFullDataApiService.class),
                mPreferencesHelper
        );
    }

    public void onDestroy() {
        sApplicationModules = null;
    }
}
