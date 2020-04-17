package com.tohsoft.airquality.data.network;



import com.tohsoft.airquality.BuildConfig;
import com.tohsoft.airquality.data.local.preference.PreferencesHelper;
import com.tohsoft.airquality.data.models.MoreApps;
import com.tohsoft.airquality.data.models.User;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by Phong on 11/9/2016.
 */

public class DataManager {
    private RemoteApiService mRemoteApiService;
    private PreferencesHelper mPreferencesHelper;

    public DataManager(RemoteApiService remoteApiService, PreferencesHelper preferencesHelper) {
        this.mRemoteApiService = remoteApiService;
        this.mPreferencesHelper = preferencesHelper;
    }

    public Observable<User> login(String email, String password, String android_push_key) {
        return mRemoteApiService.login(email, password, android_push_key);
    }

    public Observable<MoreApps> getMoreApps() {
        Map<String, String> params = new HashMap<>();
        params.put("app_id", BuildConfig.APPLICATION_ID);
        return mRemoteApiService.moreApps(params);
    }
}
