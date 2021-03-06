package com.tohsoft.airquality.helper;

import android.content.Context;

import com.google.firebase.FirebaseApp;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.tohsoft.airquality.BuildConfig;
import com.tohsoft.airquality.R;
import com.utility.DebugLog;

public class FirebaseRemoteConfigHelper {
    private static final String REMOTE_CONFIG_GET_PRO_VERSION = "get_pro_version";

    private static FirebaseRemoteConfigHelper firebaseRemoteConfigHelper;
    private FirebaseRemoteConfig mFirebaseRemoteConfig;
    private boolean isFetching = false;

    public static FirebaseRemoteConfigHelper getInstance() {
        if (firebaseRemoteConfigHelper == null) {
            firebaseRemoteConfigHelper = new FirebaseRemoteConfigHelper();
        }
        return firebaseRemoteConfigHelper;
    }

    private boolean initializeApp(Context context) {
        try {
            FirebaseApp.initializeApp(context);
            long cacheExpiration = 3600;
            if (BuildConfig.DEBUG || BuildConfig.TEST_AD) {
                cacheExpiration = 0;
            }
            mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
            FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                    .setMinimumFetchIntervalInSeconds(cacheExpiration)
                    .build();
            mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
            mFirebaseRemoteConfig.setDefaultsAsync(R.xml.remote_config_defaults);
            return true;
        } catch (Exception e) {
            DebugLog.loge(e);
            return false;
        }
    }

    public void fetchRemoteData(Context context) {
        if (mFirebaseRemoteConfig == null) {
            if (!initializeApp(context)) {
                return;
            }
        }
        if (isFetching) {
            return;
        }
        isFetching = true;
        mFirebaseRemoteConfig.fetchAndActivate()
                .addOnCompleteListener(task -> {
                    isFetching = false;
                    if (task.isSuccessful()) {
                        DebugLog.loge("Fetch Successful");
                    } else {
                        DebugLog.loge("Fetch Failed");
                    }
                });
    }

    public boolean getProVersionEnable() {
        if (mFirebaseRemoteConfig != null) {
            return mFirebaseRemoteConfig.getLong(REMOTE_CONFIG_GET_PRO_VERSION) == 1;
        }
        return false;
    }

}
